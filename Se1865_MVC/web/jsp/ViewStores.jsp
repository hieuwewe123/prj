<%-- 
    Document   : ViewStores
    Created on : June 10, 2024, 11:00 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Stores,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <% 
        // Get data from servlet (controller)
        Vector<Stores> vector = (Vector<Stores>)request.getAttribute("data");
        String paperTitle = (String)request.getAttribute("paperTitle");
        String tableTitle = (String)request.getAttribute("tableTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=paperTitle%></title>
    </head>
    <body>
        <form action="URLStores" method="get">
            <p>Search Store by ID: 
                <input type="text" name="store_id" id="">
                <input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="searchStores">
            </p>
        </form>
        <p><a href="URLStores?service=insertStore">Insert Store</a></p>
        <table border="1">
            <caption><%=tableTitle%></caption>
            <tr>
                <th>Store ID</th>
                <th>Store Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Zip Code</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (Stores store : vector) { %>
            <tr>
                <td><%=store.getStore_id()%></td>
                <td><%=store.getStore_name()%></td>
                <td><%=store.getPhone()%></td>
                <td><%=store.getEmail()%></td>
                <td><%=store.getStreet()%></td>
                <td><%=store.getCity()%></td>
                <td><%=store.getState()%></td>
                <td><%=store.getZip_code()%></td>
                <td><a href="URLStores?service=updateStore&store_id=<%=store.getStore_id()%>">Update</a></td>
                <td><a href="URLStores?service=deleteStore&store_id=<%=store.getStore_id()%>">Delete</a></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
