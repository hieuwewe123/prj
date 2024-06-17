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
            if (service.equals("listCustomer")) {
                Vector<Customers> vector = dao.getCustomers("SELECT * FROM customers");

                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "Customer Management");
                request.setAttribute("tableTitle", "List of Customers");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ViewCustomer.jsp");
                dispatcher.forward(request, response);
            } else if (service.equals("searchCustomer")) {
                int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                Vector<Customers> vector = dao.getCustomers("SELECT * FROM customers WHERE customer_id = " + customer_id);
                        

                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "Customer Search Results");
                request.setAttribute("tableTitle", "Search Results for Customer Id: " + customer_id);
                
                request.getRequestDispatcher("/jsp/ViewCustomer.jsp").forward(request, response);
            
            } else if (service.equals("insertCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // Display insert form
                    request.getRequestDispatcher("/jsp/insertCustomer.jsp").forward(request, response);
                } else { // Insert into database
                    try {
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

                        response.sendRedirect("URLCustomer?service=listCustomer");
                    } catch (NumberFormatException | IOException e) {
                        request.setAttribute("message", "Invalid input: " + e.getMessage());
                        request.getRequestDispatcher("/jsp/insertCustomer.jsp").forward(request, response);
                    }
                }
            }else if (service.equals("deleteCustomer")) {
            int customer_id = Integer.parseInt(request.getParameter("customer_id"));
            int n = dao.deleteCustomer(customer_id);

            if (n > 0) {
                request.setAttribute("message", "Customer deleted successfully!");
            } else {
                request.setAttribute("message", "Customer deletion failed.");
            }

            response.sendRedirect("URLCustomer?service=listCustomer");
        }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/error.jsp");
            dispatcher.forward(request, response);
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
        return "Controller for Customer Management";
    }
}
