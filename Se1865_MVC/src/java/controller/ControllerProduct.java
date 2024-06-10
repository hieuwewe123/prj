package controller;

import entity.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import model.DAOProducts;

@WebServlet(name = "ControllerProduct", urlPatterns = {"/URLProduct"})
public class ControllerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProducts dao = new DAOProducts();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listProduct";
        }
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listProduct")) {
                Vector<Products> vector = dao.getProducts("SELECT * FROM products");
                request.setAttribute("data", vector);
                request.setAttribute("paperTitle", "Product Management");
                request.setAttribute("tableTitle", "List of Products");
                request.getRequestDispatcher("/jsp/ViewProduct.jsp").forward(request, response);
            } else if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // display insert form
                    request.getRequestDispatcher("/jsp/insertProduct.jsp").forward(request, response);
                } else { // insert into database
                    int product_id = Integer.parseInt(request.getParameter("product_id"));
                    int model_year = Integer.parseInt(request.getParameter("model_year"));
                    double list_price = Double.parseDouble(request.getParameter("list_price"));
                    String product_name = request.getParameter("product_name");
                    String brand_name = request.getParameter("brand_name");
                    String category_name = request.getParameter("category_name");
                    Products product = new Products(product_id, model_year, list_price, product_name, brand_name, category_name);
                    int n = dao.addProduct(product);
                    response.sendRedirect("URLProducts?service=listProducts");
                }
            } else if (service.equals("searchProduct")) {
                int product_id = Integer.parseInt(request.getParameter("product_id"));
                Vector<Products> vector = dao.getProducts("SELECT * FROM products WHERE product_id = " + product_id);
                request.setAttribute("data", vector);
                request.setAttribute("paperTitle", "Product Search Results");
                request.setAttribute("tableTitle", "Search Results for Product ID: " + product_id);
                request.getRequestDispatcher("/jsp/ViewProduct.jsp").forward(request, response);
            }
            // Add updateProduct, deleteProduct, and other functionalities as needed
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
        return "Controller for Product Management";
    }
}
