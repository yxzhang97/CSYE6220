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
            margin: 0 auto;
            padding: 20px;
            background-color: #f8f8f8;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            color: #666;
            margin-bottom: 10px;
        }

        button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
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
    <h2>Orders</h2>
    <c:forEach var="order" items="${orders}">
        <c:if test="${order.valid}">
            <p>
                Order ID: ${order.id}<br>
                Date Created: ${order.dateCreated}<br>
                Total Price: $${order.totalPrice}<br>
                <a href="/order/${order.id}"><button>Detail</button></a>
            </p>
        </c:if>
    </c:forEach>

    <p><a href="/store-page">Store Home Page</a></p>
    <p><a href="/cart/all">Cart</a></p>
    <p><a href="/order/all">All Orders</a></p>
</body>
</html>
