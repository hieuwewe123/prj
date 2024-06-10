<%--
    Document   : viewOrderItem
    Created on : Jun 7, 2024, 10:29:44 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.order_items, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <%
        Vector<order_items> vector = (Vector<order_items>) request.getAttribute("data");
        String paperTitle = (String) request.getAttribute("paperTitle");
        String tableTitle = (String) request.getAttribute("tableTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= paperTitle %></title>
    </head>
    <body>
        <form action="URLOrderItems" method="get">
        Order ID: <input type="text" name="order_id">
        <input type="submit" name="submit" value="Search">
        <input type="hidden" name="service" value="listOrderItems">
    </form>
        <p><a href="URLOrderItems?service=insertOrderItem">Insert Order Item</a></p>
        <table border="1">
            <caption><%= tableTitle %></caption>
            <tr>
                <th>Order ID</th>
                <th>Item ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>List Price</th>
                <th>Discount</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (order_items item : vector) { %>
                <tr>
                    <td><%= item.getOrder_id() %></td>
                    <td><%= item.getItem_id() %></td>
                    <td><%= item.getProduct_id() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getList_price() %></td>
                    <td><%= item.getDiscount() %></td>
                    <td><a href="URLOrderItem?service=updateOrderItem&order_id=<%= item.getOrder_id() %>&item_id=<%= item.getItem_id() %>">Update</a></td>
                    <td><a href="URLOrderItem?service=deleteOrderItem&order_id=<%= item.getOrder_id() %>&item_id=<%= item.getItem_id() %>">Delete</a></td>
                </tr>
            <% } %>
        </table>
    </body>
</html>
