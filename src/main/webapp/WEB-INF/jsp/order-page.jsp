<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0 auto;
            padding: 20px;
            background-color: #f8f8f8;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        h3 {
            color: #666;
            margin-top: 20px;
        }

        p {
            color: #666;
            margin: 10px 0;
        }

        button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            margin-top: 10px;
        }

        button:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #069;
            margin: 10px;
        }

        a:hover {
            color: #09f;
        }
    </style>
</head>

<body>
    <h2>Order Details</h2>
    <h3>Order ID: ${orderEntity.id}</h3>
    <h3>Total Price: ${orderEntity.totalPrice}</h3>

    <c:forEach var="orderItem" items="${orderItems}">
        <p>
            Item Name: ${orderItem.itemEntity.name}<br>
            Unit Price: $${orderItem.unitPrice}<br>
            Total Price: $${orderItem.totalPrice}<br>
            Amount: ${orderItem.amount}<br>
            <a href="/comment/new/${orderItem.id}"><button>Write a Comment</button></a>
        </p>
    </c:forEach>

    <p><a href="/store-page">Store Home Page</a></p>
</body>
</html>
