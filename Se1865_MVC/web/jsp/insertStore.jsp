<%--
    Document   : insertStore
    Created on : Jun 14, 2024, 10:29:44 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Store</title>
    </head>
    <body>
        <form action="URLStores" method="post">
            <table>
                <tr>
                    <td>Store ID</td>
                    <td><input type="text" name="store_id"></td>
                </tr>
                <tr>
                    <td>Store Name</td>
                    <td><input type="text" name="store_name"></td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="phone"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td>Street</td>
                    <td><input type="text" name="street"></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" name="city"></td>
                </tr>
                <tr>
                    <td>State</td>
                    <td><input type="text" name="state"></td>
                </tr>
                <tr>
                    <td>Zip Code</td>
                    <td><input type="text" name="zip_code"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="submit" value="Insert Store"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="reset" value="Reset"></td>
                </tr>
                <input type="hidden" name="service" value="insertStore">
            </table>
        </form>
    </body>
</html>
