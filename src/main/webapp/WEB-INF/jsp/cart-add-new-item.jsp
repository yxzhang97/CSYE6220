<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Item Added to Cart</title>
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
    <h2>New Item Added to Cart</h2>
    <p>Item Name: ${cartItemEntity.itemEntity.name}</p>
    <p>Unit Price: ${cartItemEntity.unitPrice}</p>
    <p>Total Price: ${cartItemEntity.totalPrice}</p>
    <p>Amount: ${cartItemEntity.amount}</p>

    <p><a href="/store-page">Store Home Page</a></p>
    <p><a href="/cart/all">Cart</a></p>
    <p><a href="/order/all">Orders</a></p>
</body>
</html>
