<%-- 
    Document   : insertCustomer
    Created on : Jun 10, 2024
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert Customer</title>
</head>
<body>
    <form action="URLCustomer" method="post">
        <table>
            <tr>
                <td>Customer ID</td>
                <td><input type="text" name="customer_id" id=""></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="first_name" id=""></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="last_name" id=""></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" id=""></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" id=""></td>
            </tr>
            <tr>
                <td>Street</td>
                <td><input type="text" name="street" id=""></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="city" id=""></td>
            </tr>
            <tr>
                <td>State</td>
                <td><input type="text" name="state" id=""></td>
            </tr>
            <tr>
                <td>Zip Code</td>
                <td><input type="text" name="zip_code" id=""></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" value="Add Customer"></td>
                <td><input type="reset" value="Reset">
                    <input type="hidden" name="service" value="insertCustomer">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
