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

        p {
            color: #666;
            margin: 10px 0;
        }

        img {
            max-width: 100%;
            height: auto;
            margin: 10px 0;
        }

        select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
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
    <h2>Item Detail</h2>
    <p>Item Name: ${itemEntity.name}</p>
    <p>SKU: ${itemEntity.sku}</p>
    <p>Unit Price: ${itemEntity.price}</p>
    <p>Inventory: ${itemEntity.inventory}</p>
    <p>Manufacturer: ${itemEntity.manufacturer}</p>
    <p>Description: ${itemEntity.description}</p>

    <c:forEach var="url" items="${itemEntity.url2media}">
        <img src="${url}" width="50%" height="50%" alt="Alternate Text">
    </c:forEach>

    <form action="/cart/newItem/${itemEntity.id}" method="post">
        <label for="amount">Amount:</label>
        <select name="amount" id="amount">
            <c:forEach var="i" begin="1" end="${itemEntity.inventory}">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>
        <br><br>
        <input type="submit" value="Add to Cart">
    </form>

    <h2>Comments and Reviews</h2>
    <c:forEach var="comment" items="${itemEntity.comments}">
        <p>Date: ${comment.dateCreated}</p>
        <p>User: ${comment.userEntity.nickName}</p>
        <p>${comment.text}</p>
        <br>
    </c:forEach>

    <p><a href="/store-page">Store Home Page</a></p>
    <p><a href="/cart/all">Cart</a></p>
    <p><a href="/order/all">Orders</a></p>
</body>
</html>
