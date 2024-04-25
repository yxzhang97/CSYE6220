<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New Item</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        form {
            margin-top: 20px;
        }

        p {
            color: #666;
            margin: 10px 0;
        }

        input[type="text"], input[type="submit"] {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #069;
            margin: 10px 0;
            display: inline-block;
        }

        a:hover {
            color: #09f;
        }
    </style>
</head>
<body>
    <form action="/item/newItem" method="post">
        <p>Item Name: <input type="text" name="name"></p>
        <p>SKU: <input type="text" name="sku"></p>
        <p>Price: <input type="text" name="price"></p>
        <p>Inventory: <input type="text" name="inventory"></p>
        <p>Manufacturer: <input type="text" name="manufacturer"></p>
        <p>Description: <input type="text" name="description"></p>
        <input type="submit" value="Submit">
    </form>

    <p><a href="/item/all">All Items</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
