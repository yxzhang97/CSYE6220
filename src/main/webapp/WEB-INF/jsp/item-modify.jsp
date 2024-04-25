<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Item Detail</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        h2 {
            color: #333;
            margin-top: 20px;
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

        button {
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            padding: 10px 20px;
            text-decoration: none;
        }

        button:hover {
            background-color: #d32f2f;
        }

        img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
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
    <h2>Item Detail</h2>
    <form action="/item/modify/${itemEntity.id}" method="post">
        <p>Item Name: <input type="text" name="name" value="${itemEntity.name}"></p>
        <p>SKU: <input type="text" name="sku" value="${itemEntity.sku}"></p>
        <p>Unit Price: <input type="text" name="price" value="${itemEntity.price}"></p>
        <p>Inventory: <input type="text" name="inventory" value="${itemEntity.inventory}"></p>
        <p>Manufacturer: <input type="text" name="manufacturer" value="${itemEntity.manufacturer}"></p>
        <p>Description: <input type="text" name="description" value="${itemEntity.description}"></p>
        <input type="submit" value="Submit">
    </form>

    <p><a href="/item/delete/${itemEntity.id}"><button>Delete</button></a></p>

    <c:forEach var="url" items="${itemEntity.url2media}">
        <p><img src="${url}" alt="Alternate Text"></p>
    </c:forEach>

    <p><a href="/item/all">All Items</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
