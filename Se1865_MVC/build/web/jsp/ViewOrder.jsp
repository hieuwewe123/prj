<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<%
    Vector<Orders> vector = (Vector<Orders>) request.getAttribute("data");
    String paperTitle = (String) request.getAttribute("paperTitle");
    String tableTitle = (String) request.getAttribute("tableTitle");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%= paperTitle %></title>
</head>
<body>
    <form action="URLOrder" method="get">
        <p>Search Order by Order ID: 
            <input type="text" name="order_id" id="">
            <input type="submit" name="submit" value="Search">
            <input type="reset" value="Clear">
            <input type="hidden" name="service" value="listOrder">
        </p>
    </form>
    <p><a href="URLOrder?service=insertOrder">Insert Order</a></p>
    <table border="1">
        <caption><%= tableTitle %></caption>
        <tr>
            <th>Order ID</th>
            <th>Customer ID</th>
            <th>Order Status</th>
            <th>Store ID</th>
            <th>Staff ID</th>
            <th>Order Date</th>
            <th>Required Date</th>
            <th>Shipped Date</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <% for (Orders order : vector) { %>
        <tr>
            <td><%= order.getOrder_id() %></td>
            <td><%= order.getCustomer_id() %></td>
            <td><%= order.getOrder_status() %></td>
            <td><%= order.getStore_id() %></td>
            <td><%= order.getStaff_id() %></td>
            <td><%= order.getOrder_date() %></td>
            <td><%= order.getRequired_date() %></td>
            <td><%= order.getShipped_date() %></td>
            <td><a href="URLOrder?service=updateOrder&order_id=<%= order.getOrder_id() %>">Update</a></td>
            <td><a href="URLOrder?service=deleteOrder&order_id=<%= order.getOrder_id() %>">Delete</a></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
