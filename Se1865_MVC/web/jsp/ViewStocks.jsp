<%-- 
    Document   : ViewStocks
    Created on : June 10, 2024, 11:30 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Stocks,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <% 
        // Get data from servlet (controller)
        Vector<Stocks> vector = (Vector<Stocks>)request.getAttribute("data");
        String paperTitle = (String)request.getAttribute("paperTitle");
        String tableTitle = (String)request.getAttribute("tableTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=paperTitle%></title>
    </head>
    <body>
        <form action="URLStocks" method="get">
            <p>Search Stock by Store ID: 
                <input type="text" name="store_id" id="">
                <input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listStock">
            </p>
        </form>
        <p><a href="URLStocks?service=insertStock">Insert Stock</a></p>
        <table border="1">
            <caption><%=tableTitle%></caption>
            <tr>
                <th>Store ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (Stocks stock : vector) { %>
            <tr>
                <td><%=stock.getStore_id()%></td>
                <td><%=stock.getProduct_id()%></td>
                <td><%=stock.getQuantity()%></td>
                <td><a href="URLStocks?service=updateStock&store_id=<%=stock.getStore_id()%>&product_id=<%=stock.getProduct_id()%>">Update</a></td>
                <td><a href="URLStocks?service=deleteStock&store_id=<%=stock.getStore_id()%>&product_id=<%=stock.getProduct_id()%>">Delete</a></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
