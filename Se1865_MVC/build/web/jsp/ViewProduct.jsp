<%-- 
    Document   : ViewProducts
    Created on : June 10, 2024, 11:00 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Products"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Management</title>
</head>
<body>
    <h2>Product Management</h2>
    
    <form action="URLProduct" method="get">
        <p>
            Search Product by ID: 
            <input type="text" name="product_id">
            <input type="submit" name="submit" value="Search">
            <input type="reset" value="Clear">
            <input type="hidden" name="service" value="searchProduct">
        </p>
    </form>
    
    <p><a href="URLProduct?service=insertProduct">Insert Product</a></p>
    
    <table border="1">
        <caption>List of Products</caption>
        <tr>
            <th>Product ID</th>
            <th>Model Year</th>
            <th>List Price</th>
            <th>Product Name</th>
            <th>Brand Name</th>
            <th>Category Name</th>
            <th>Action</th>
        </tr>
        <% Vector<Products> vector = (Vector<Products>) request.getAttribute("data");
           String tableTitle = (String) request.getAttribute("tableTitle");
           for (Products product : vector) { %>
        <tr>
            <td><%= product.getProduct_id() %></td>
            <td><%= product.getModel_year() %></td>
            <td><%= product.getList_price() %></td>
            <td><%= product.getProduct_name() %></td>
            <td><%= product.getBrand_name() %></td>
            <td><%= product.getCategory_name() %></td>
            <td>
                <a href="URLProducts?service=updateProduct&product_id=<%= product.getProduct_id() %>">Update</a> |
                <a href="URLProducts?service=deleteProduct&product_id=<%= product.getProduct_id() %>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
