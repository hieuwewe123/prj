package controller;

import entity.Customers;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOCustomers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;

@WebServlet(name = "ControllerCustomer", urlPatterns = {"/URLCustomer"}, initParams = {
        @WebInitParam(name = "URL", value = "URLDemo"),
        @WebInitParam(name = "userName", value = "sa"),
        @WebInitParam(name = "pass", value = "123456")})
public class ControllerCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCustomers dao = new DAOCustomers();
        String service = request.getParameter("service");

        if (service == null) {
            service = "listCustomer";
        }

        try (PrintWriter out = response.getWriter()) {
            switch (service) {
                case "insertCustomer":
                    handleInsertCustomer(request, response, dao);
                    break;
                case "listCustomer":
                default:
                    handleListCustomer(request, response, dao);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void handleInsertCustomer(HttpServletRequest request, HttpServletResponse response, DAOCustomers dao)
            throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit == null) { // Display insert form
            request.getRequestDispatcher("/jsp/insertCustomer.jsp").forward(request, response);
        } else { // Insert into database
            int customer_id = Integer.parseInt(request.getParameter("customer_id"));
            String first_name = request.getParameter("first_name");
            String last_name = request.getParameter("last_name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip_code = request.getParameter("zip_code");

            Customers customer = new Customers(customer_id, first_name, last_name, phone, email, street, city, state, zip_code);
            int n = dao.addCustomer(customer);

            if (n > 0) {
                request.setAttribute("message", "Customer inserted successfully!");
            } else {
                request.setAttribute("message", "Customer insertion failed.");
            }

            request.getRequestDispatcher("URLCustomer?service=listCustomer").forward(request, response);
        }
    }

    private void handleListCustomer(HttpServletRequest request, HttpServletResponse response, DAOCustomers dao)
            throws ServletException, IOException {
        Vector<Customers> vector;
        String submit = request.getParameter("submit");

        if (submit == null) {
            vector = dao.getCustomers("SELECT * FROM customers");
        } else {
            String name = request.getParameter("customerName");
            String sql = "SELECT * FROM customers WHERE first_name LIKE ? OR last_name LIKE ?";
            vector = dao.getCustomersWithPreparedStatement(sql, "%" + name + "%");
        }

        request.setAttribute("data", vector);
        request.setAttribute("paperTitle", "Customer Management");
        request.setAttribute("tableTitle", "List of Customers");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ViewCustomer.jsp");
        dispatcher.forward(request, response);
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
        return "Controller for Customer Management";
    }
}
