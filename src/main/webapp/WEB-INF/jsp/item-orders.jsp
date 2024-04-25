<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
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
        }

        p {
            color: #666;
            margin: 10px 0;
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
    <h2>Orders</h2>
    <c:forEach var="orderItem" items="${orderItems}">
        <p>
            Order ID: ${orderItem.id}<br>
            Date Created: ${orderItem.dateCreated}<br>
            Date Last Modified: ${orderItem.dateLastModified}<br>
            Amount: ${orderItem.amount}<br>
            Total Price: $${orderItem.totalPrice}<br>
            Delivery Address: ${orderItem.orderEntity.addressEntity.state} ${orderItem.orderEntity.addressEntity.city} ${orderItem.orderEntity.addressEntity.street} ${orderItem.orderEntity.addressEntity.aptNumber} ${orderItem.orderEntity.addressEntity.zipCode}
        </p>
    </c:forEach>

    <p><a href="/item/all">Item All</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
