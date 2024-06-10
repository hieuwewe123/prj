package controller;

import entity.Stocks;
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
import model.DAOStocks;

@WebServlet(name = "ControllersStocks", urlPatterns = {"/URLStocks"}, initParams = {
    @WebInitParam(name = "URL", value = "URLDemo"),
    @WebInitParam(name = "userName", value = "sa"),
    @WebInitParam(name = "pass", value = "123456")})
public class ControllersStocks extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOStocks dao = new DAOStocks();

        // Get request parameters from view
        String service = request.getParameter("service");
        if (service == null) {
            service = "listStocks";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listStocks")) {
                // Call DAO to fetch stocks
                String submit = request.getParameter("submit");
                Vector<Stocks> vector;
                if (submit == null) { // List all stocks
                    vector = dao.getStocks("SELECT * FROM stocks");
                } else { // List search results
                    String productId = request.getParameter("productId");
                    vector = dao.getStocks("SELECT * FROM stocks"
                            + " WHERE product_id = " + productId);
                }

                // Set data for view
                request.setAttribute("data", vector);
                request.setAttribute("pageTitle", "Stock Management");
                request.setAttribute("tableTitle", "List of Stocks");

                // Select view
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/jsp/ViewStocks.jsp");
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
        return "Controller for managing stocks";
    }

}
