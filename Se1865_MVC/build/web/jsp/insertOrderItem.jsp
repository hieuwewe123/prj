<%--
    Document   : insertOrderItem
    Created on : Jun 7, 2024, 10:29:44 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="URLOrderItems" method="post">
            <table>
                <tr>
                    <td>Order ID</td>
                    <td><input type="text" name="order_id"></td>
                </tr>
                <tr>
                    <td>Item ID</td>
                    <td><input type="text" name="item_id"></td>
                </tr>
                <tr>
                    <td>Product ID</td>
                    <td><input type="text" name="product_id"></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity"></td>
                </tr>
                <tr>
                    <td>List Price</td>
                    <td><input type="text" name="list_price"></td>
                </tr>
                <tr>
                    <td>Discount</td>
                    <td><input type="text" name="discount"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="submit" value="Add Order Item"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="reset" value="Reset"></td>
                </tr>
                <input type="hidden" name="service" value="insertOrderItem">
            </table>
        </form>
    </body>
</html>
