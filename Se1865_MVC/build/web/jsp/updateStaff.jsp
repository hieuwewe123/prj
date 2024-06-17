<%-- 
    Document   : insertStaff
    Created on : Jun 7, 2024, 10:29:44 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,entity.Staffs,java.util.Vector" %>
<!DOCTYPE html>
<html>
    <%
              ResultSet rsStore= (ResultSet)request.getAttribute("rsStore");
              ResultSet rsManage=(ResultSet)request.getAttribute("rsManage");
              Vector<Staffs> vector=(Vector<Staffs>)request.getAttribute("data");
              Staffs staff=vector.get(0);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="URLStaff" method="post">
            <table>
                <tr>
                    <td>staff_id</td>
                    <td><input type="text" name="staff_id" id="" value="<%=staff.getStaff_id()%>" readonly></td>
                </tr>
                <tr>
                    <td>first_name</td>
                    <td><input type="text" name="first_name" id="" value="<%=staff.getFirst_name()%>"></td>
                </tr>
                <tr>
                    <td>last_name</td>
                    <td><input type="text" name="last_name" id="" value="<%=staff.getLast_name()%>"></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input type="email" name="email" id="" value="<%=staff.getEmail()%>"></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><input type="text" name="phone" id="" value="<%=staff.getPhone()%>"></td>
                </tr>
                <tr>
                    <td>active</td>
                    <td>
                        <input type="radio" name="active" id="" value="1" <%=(staff.getActive()==1?"checked":"")%>>active
                        <input type="radio" name="active" id="" value="0" <%=(staff.getActive()==0?"checked":"")%>>Deactive
                    </td>
                </tr>
                <tr>
                    <td>store_id</td>
                    <td>
                        <select name="store_id" id="">
                            <% while(rsStore.next()){%>
                            <option value="<%=rsStore.getInt(1)%>"><%=rsStore.getString(2)%></option>
                            <%}%>

                        </select>
                    </td>
                </tr>
                <tr>
                    <td>manager_id</td>
                    <td>
                        <select name="manager_id" id="">
                            <% while(rsManage.next()){%>
                            <option value="<%=rsManage.getInt(1)%>"><%=rsManage.getString(2)%> 
                                <%=rsManage.getString(3)%></option>
                            <%}%>

                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="add Staff"></td>
                    <td><input type="reset" value="reset">
                        <input type="hidden" name="service" value="updateStaff">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
