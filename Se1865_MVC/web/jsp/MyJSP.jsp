<%-- 
    Document   : MyJSP
    Created on : Jun 4, 2024, 7:52:57 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Staffs" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p> jsp script: java command/servlet
        <!--jsp script-->    
        <% //code java here
            int MAX=1000; //Local variable
            out.print("<h2> max="+MAX+"</h2>");
        %>
        <p> jsp expression</p>
        <H1> double MAX=<%=MAX*2%> </H1>
        <%for(int i=20;i<=MAX;i+=20){%>
            <hr width="<%=i%>" />
        <%}%>
        <P>jsp declare</P>
        <%! int MIN=1;  //MIN is global variable%>
        <%! String welCome(String name){
            return "welcome "+name + " Min="+MIN;
        }%>
        <h2><%=welCome("John")%> </h2>
    </body>
</html>
