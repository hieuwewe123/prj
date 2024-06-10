<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Product</title>
    </head>
    <body>
        <h2>Insert Product</h2>
        <form action="URLProducts" method="post">
            <input type="hidden" name="service" value="insertProduct">
            Product ID: <input type="text" name="product_id"><br><br>
            Model Year: <input type="text" name="model_year"><br><br>
            List Price: <input type="text" name="list_price"><br><br>
            Product Name: <input type="text" name="product_name"><br><br>
            Brand Name: <input type="text" name="brand_name"><br><br>
            Category Name: <input type="text" name="category_name"><br><br>
            <input type="submit" name="submit" value="Insert Product">
        </form>
    </body>
</html>
