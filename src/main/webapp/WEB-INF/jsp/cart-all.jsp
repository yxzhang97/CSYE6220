<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
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

        h3 {
            color: #666;
            margin: 10px 0;
        }

        p {
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
    </style>
</head>
<body>
    <h2>Shopping Cart</h2>
    <h3>Total Price: ${cartEntity.totalPrice}</h3>
    <h3>Number of Items: ${cartEntity.numOfItems}</h3>

    <c:forEach var="cartItem" items="${cartEntity.cartItems}">
        <c:if test="${cartItem.valid}">
            <p>
                Item Name: ${cartItem.itemEntity.name}<br>
                Unit Price: $${cartItem.unitPrice}<br>
                Total Price: $${cartItem.totalPrice}<br>
                Amount: ${cartItem.amount}<br>
                <a href="/cart/delete/${cartItem.id}"><button>Remove</button></a>
            </p>
        </c:if>
    </c:forEach>

    <a href="/order/newOrder/pre"><button>Check Out</button></a>

    <p><a href="/store-page">Store Home Page</a></p>
    <p><a href="/cart/all">Cart</a></p>
    <p><a href="/order/all">Orders</a></p>
</body>
</html>
