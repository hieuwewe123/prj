<%-- 
    Document   : ViewCustomer
    Created on : Jun 10, 2024
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Customers,java.util.Vector" %>
<!DOCTYPE html>
<html>
<% // Get data from servlet (controller)
    Vector<Customers> vector = (Vector<Customers>) request.getAttribute("data");
    String paperTitle = (String) request.getAttribute("paperTitle");
    String tableTitle = (String) request.getAttribute("tableTitle");
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=paperTitle%></title>
</head>
<body>
    <form action="URLCustomer" method="get">
        <p>Search Customer by ID:
            <input type="text" name="customer_id" id="">
            <input type="submit" name="submit" value="Search">
            <input type="reset" value="Clear">
            <input type="hidden" name="service" value="searchCustomer">
        </p>
    </form>
    <p><a href="URLCustomer?service=insertCustomer">Insert Customer</a></p>
    <table border="1">
        <caption><%=tableTitle%></caption>
        <tr>
            <th>Customer ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Street</th>
            <th>City</th>
            <th>State</th>
            <th>Zip Code</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <% for (Customers customer : vector) { %>
        <tr>
            <td><%=customer.getCustomer_id()%></td>
            <td><%=customer.getFirst_name()%></td>
            <td><%=customer.getLast_name()%></td>
            <td><%=customer.getPhone()%></td>
            <td><%=customer.getEmail()%></td>
            <td><%=customer.getStreet()%></td>
            <td><%=customer.getCity()%></td>
            <td><%=customer.getState()%></td>
            <td><%=customer.getZip_code()%></td>
            <td><a href="URLCustomer?service=updateCustomer&customer_id=<%=customer.getCustomer_id()%>">Update</a></td>
            <td><a href="URLCustomer?service=deleteCustomer&customer_id=<%=customer.getCustomer_id()%>">Delete</a></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
