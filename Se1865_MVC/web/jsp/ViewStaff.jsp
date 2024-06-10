<%-- 
    Document   : ViewStaff
    Created on : Jun 4, 2024, 8:46:21 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Staffs,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <% //get data from servlet (controller)
        Vector<Staffs> vector=(Vector<Staffs>)request.getAttribute("data");
        String paperTitle= (String)request.getAttribute("paperTitle");
        String tableTitle=(String)request.getAttribute("tableTitle");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=paperTitle%></title>
    </head>
    <body>
        <form accept="URLStaff" method="get">
            <p>Search Staff by Name: 
                <input type="text" name="first_name" id="">
                <input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listStaff">
            </p>
        </form>
        <p><a href="URLStaff?service=insertStaff">insert Staffs</a></p>
        <table border="1">
            <caption><%=tableTitle%></caption>
            <tr>
                <th>staff_id</th>
                <th>first_name</th>
                <th>last_name</th>
                <th>email</th>
                <th>phone</th>
                <th>active</th>
                <th>store_id</th>
                <th>manager_id</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <% for (Staffs staff : vector) { %>
            <tr>
                <td><%=staff.getStaff_id()%></td>
                <td><%=staff.getFirst_name()%></td>
                <td><%=staff.getLast_name()%></td>
                <td><%=staff.getEmail()%></td>
                <td><%=staff.getPhone()%></td>
                <td><%=staff.getActive()%></td>
                <td><%=staff.getStore_id()%></td>
                <td><%=staff.getManager_id()%></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
