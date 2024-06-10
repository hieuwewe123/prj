package controller;

import entity.order_items;
import model.DAOOrder_items;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebInitParam;
import java.util.Vector;

@WebServlet(name = "ControllerOrderItems", urlPatterns = {"/URLOrderItems"}, initParams = {
    @WebInitParam(name = "URL", value = "URLDemo"),
    @WebInitParam(name = "userName", value = "sa"),
    @WebInitParam(name = "pass", value = "123456")})
public class ControllerOrderItems extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrder_items dao = new DAOOrder_items();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listOrderItems";
        }
        try {
            if (service.equals("insertOrderItem")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // Display insert form
                    request.getRequestDispatcher("/jsp/insertOrderItem.jsp").forward(request, response);
                } else { // Insert into database
                    int order_id = Integer.parseInt(request.getParameter("order_id"));
                    int item_id = Integer.parseInt(request.getParameter("item_id"));
                    int product_id = Integer.parseInt(request.getParameter("product_id"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double list_price = Double.parseDouble(request.getParameter("list_price"));
                    double discount = Double.parseDouble(request.getParameter("discount"));
                    order_items orderItem = new order_items(order_id, item_id, product_id, quantity, list_price, discount);
                    int n = dao.addOrderItem(orderItem);
                    response.sendRedirect("URLOrderItems?service=listOrderItems");
                }
            } else if (service.equals("listOrderItems")) {
                Vector<order_items> vector;
                String submit = request.getParameter("submit");
                if (submit == null) { // List all
                    vector = dao.getOrderItems("SELECT * FROM order_items");
                } else { // List search
                    String orderIdStr = request.getParameter("order_id");
                    int orderId = orderIdStr != null && !orderIdStr.isEmpty() ? Integer.parseInt(orderIdStr) : -1;
                    String sql = "SELECT * FROM order_items WHERE 1=1";
                    if (orderId != -1) {
                        sql += " AND order_id = " + orderId;
                    }
                    vector = dao.getOrderItems(sql);
                }
                request.setAttribute("data", vector);
                request.setAttribute("paperTitle", "Order Items Management");
                request.setAttribute("tableTitle", "List of Order Items");
                RequestDispatcher dispth = request.getRequestDispatcher("/jsp/ViewOrderItems.jsp");
                dispth.forward(request, response);
            }
            // Implement other services like updateOrderItem, deleteOrderItem, etc.
        } catch (Exception ex) {
            ex.printStackTrace();
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
        return "Controller for Order Items Management";
    }
}
