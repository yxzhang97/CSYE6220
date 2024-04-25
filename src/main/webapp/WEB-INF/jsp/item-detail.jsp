<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Item Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            color: #666;
            margin: 10px 0;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            padding: 8px 16px;
            text-decoration: none;
        }

        button:hover {
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

        form {
            display: inline;
        }
    </style>
</head>
<body>
    <ul>
        <li>Item Name: ${itemEntity.name}</li>
        <li>SKU: ${itemEntity.sku}</li>
        <li>Unit Price: ${itemEntity.price}</li>
        <li>Inventory: ${itemEntity.inventory}</li>
        <li>Manufacturer: ${itemEntity.manufacturer}</li>
        <li>Description: ${itemEntity.description}</li>
    </ul>

    <a href="/item/modify/${itemEntity.id}"><button>Modify Item</button></a>

    <a href="/item/${itemEntity.id}/orders"><button>Item Orders</button></a>

    <p><a href="/item/all">All Items</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
