package controller;

import entity.Stores;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.util.Vector;
import model.DAOStores;

@WebServlet(name = "ControllerStores", urlPatterns = {"/URLStores"}, initParams = {
    @WebInitParam(name = "URL", value = "URLDemo"),
    @WebInitParam(name = "userName", value = "sa"),
    @WebInitParam(name = "pass", value = "123456")})
public class ControllerStores extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStores dao = new DAOStores();

        // Get request parameters from view
        String service = request.getParameter("service");
        if (service == null) {
            service = "listStores";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listStores")) {
                // Call DAO to fetch stores
                String submit = request.getParameter("submit");
                Vector<Stores> vector;
                if (submit == null) { // List all stores
                    vector = dao.getStores("SELECT * FROM stores");
                } else { // List search results
                    String storeName = request.getParameter("storeName");
                    vector = dao.getStores("SELECT * FROM stores"
                            + " WHERE store_name like '%" + storeName + "%'");
                }

                // Set data for view
                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "Store Management");
                request.setAttribute("tableTitle", "List of Stores");

                // Select view
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/jsp/ViewStores.jsp");
                // Forward to view
                dispatcher.forward(request, response);
            }
        }
    }

    // Handles the HTTP GET method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Handles the HTTP POST method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Returns a short description of the servlet
    @Override
    public String getServletInfo() {
        return "Controller for managing stores";
    }

}
