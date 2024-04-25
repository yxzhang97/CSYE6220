<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Placed</title>
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
    <h2>Order Placed</h2>
    <h3>Order ID: ${orderEntity.id}</h3>
    <h3>Total Price: ${orderEntity.totalPrice}</h3>
    <h3>Number of Items: ${orderEntity.numOfItems}</h3>
    <h3>Delivery Address: ${deliveryAddress.state} ${deliveryAddress.city} ${deliveryAddress.street} ${deliveryAddress.aptNumber} ${deliveryAddress.zipCode}</h3>

    <p>Items:</p>
    <c:forEach var="orderItem" items="${orderItems}">
        <c:if test="${orderItem.valid}">
            <p>
                Item Name: ${orderItem.itemEntity.name}<br>
                Unit Price: $${orderItem.unitPrice}<br>
                Total Price: $${orderItem.totalPrice}<br>
                Amount: ${orderItem.amount}<br>
            </p>
        </c:if>
    </c:forEach>

    <p><a href="/store-page">Store Home Page</a></p>
    <p><a href="/cart/all">Cart</a></p>
    <p><a href="/order/all">All Orders</a></p>
</body>
</html>
