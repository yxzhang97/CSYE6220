<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Delivery Address</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
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
    <h2>Add New Delivery Address</h2>
    <form action="/account-info/delivery-addresses/new" method="post">
        <p>State: <input type="text" name="state" value="${addressEntity.state}"></p>
        <p>City: <input type="text" name="city" value="${addressEntity.city}"></p>
        <p>Street: <input type="text" name="street" value="${addressEntity.street}"></p>
        <p>Apt Number: <input type="text" name="aptNumber" value="${addressEntity.aptNumber}"></p>
        <p>Zip Code: <input type="text" name="zipCode" value="${addressEntity.zipCode}"></p>
        <input type="submit" value="Submit">
    </form>

    <p><a href="/account-info/delivery-addresses">Delivery Addresses</a></p>
    <p><a href="/account-info/basic">Account Info Basic</a></p>
    <p><a href="/login/user/logout">Logout</a></p>
</body>
</html>
