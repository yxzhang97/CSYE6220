<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0 auto;
            padding: 20px;
            background-color: #f8f8f8;
        }

        h2 {
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        input[type="text"], input[type="submit"] {
            padding: 8px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .item {
            display: inline-block;
            margin: 10px;
            text-decoration: none;
            color: #333;
        }

        .item img {
            display: block;
            width: 300px;
            height: 200px;
            margin-bottom: 10px;
        }

        .item-name {
            font-weight: bold;
        }

        .item-price {
            color: #4CAF50;
        }

        a {
            text-decoration: none;
            color: #333;
        }

        a:hover {
            color: #45a049;
        }

        .footer {
            margin-top: 50px;
        }
    </style>
</head>
<body>

<h2>Search Result</h2>

<form action="/store-page/search" method="get">
    <input type="text" name="keyword" value="${keyword}" placeholder="Search...">
    <input type="submit" value="Search">
</form>

<c:forEach var="item" items="${items}">
    <div class="item">
        <a href="/store-page/item-detail/${item.id}">
            <img src="${item.getCover()}" width="150" height="175" alt="cover image">
            <p class="item-name">${item.name}</p>
            <p class="item-price">$${item.price}</p>
        </a>
    </div>
</c:forEach>

<div class="footer">
    <p><a href="/store-page">Store Home Page</a></p>
    <p><a href="/cart/all">Cart</a></p>
    <p><a href="/order/all">Orders</a></p>
</div>

</body>
</html>

