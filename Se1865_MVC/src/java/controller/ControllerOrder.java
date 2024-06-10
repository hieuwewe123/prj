package controller;

import entity.Orders;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOOrders;

@WebServlet(name = "ControllerOrder", urlPatterns = {"/URLOrder"}, initParams = {
    @WebInitParam(name = "URL", value = "URLDemo"),
    @WebInitParam(name = "userName", value = "sa"),
    @WebInitParam(name = "pass", value = "123456")})
public class ControllerOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrders dao = new DAOOrders();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listOrder";
        }
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listOrder")) {
                String submit = request.getParameter("submit");
                Vector<Orders> vector;
                if (submit == null) { // List all orders
                    vector = dao.getOrders("SELECT * FROM orders");
                } else { // Search by order ID
                    String orderIdStr = request.getParameter("order_id");
                    int orderId = orderIdStr != null && !orderIdStr.isEmpty() ? Integer.parseInt(orderIdStr) : -1;
                    String sql = "SELECT * FROM orders WHERE 1=1";
                    if (orderId != -1) {
                        sql += " AND order_id = " + orderId;
                    }
                    vector = dao.getOrders(sql);
                }
                request.setAttribute("data", vector);
                request.setAttribute("paperTitle", "Order Management");
                request.setAttribute("tableTitle", "List of Orders");
                RequestDispatcher dispth = request.getRequestDispatcher("/jsp/ViewOrder.jsp");
                dispth.forward(request, response);
            } else if (service.equals("insertOrder")) {
                int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                int order_status = Integer.parseInt(request.getParameter("order_status"));
                int store_id = Integer.parseInt(request.getParameter("store_id"));
                int staff_id = Integer.parseInt(request.getParameter("staff_id"));
                Date order_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("order_date"));
                Date required_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("required_date"));
                Date shipped_date = null;
                if (request.getParameter("shipped_date") != null && !request.getParameter("shipped_date").isEmpty()) {
                    shipped_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("shipped_date"));
                }
                Orders order = new Orders(0, customer_id, order_status, store_id, staff_id, order_date, required_date, shipped_date);
                int n = dao.addOrder(order);
                if (n > 0) {
                    response.sendRedirect("URLOrder?service=listOrder");
                } else {
                    out.println("Failed to insert order.");
                }
            }
            // Implement other services like updateOrder, deleteOrder, etc.
        } catch (ParseException ex) {
            Logger.getLogger(ControllerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controller for Order Management";
    }
}
