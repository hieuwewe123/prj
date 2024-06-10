<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert Order</title>
</head>
<body>
    <h2>Insert New Order</h2>
    <form action="URLOrder" method="post">
        <input type="hidden" name="service" value="insertOrder">
        <label for="customer_id">Customer ID:</label>
        <input type="text" id="customer_id" name="customer_id" required><br><br>
        
        <label for="order_status">Order Status:</label>
        <input type="text" id="order_status" name="order_status" required><br><br>
        
        <label for="store_id">Store ID:</label>
        <input type="text" id="store_id" name="store_id" required><br><br>
        
        <label for="staff_id">Staff ID:</label>
        <input type="text" id="staff_id" name="staff_id" required><br><br>
        
        <label for="order_date">Order Date:</label>
        <input type="date" id="order_date" name="order_date" required><br><br>
        
        <label for="required_date">Required Date:</label>
        <input type="date" id="required_date" name="required_date" required><br><br>
        
        <label for="shipped_date">Shipped Date:</label>
        <input type="date" id="shipped_date" name="shipped_date"><br><br>
        
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>
</body>
</html>
