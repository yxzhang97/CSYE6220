<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Items</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
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
    <h2>Items</h2>
    <c:forEach var="item" items="${items}">
        <c:if test="${item.valid}">
            <p>
                ${item.name}
                <a href="/item/${item.id}"><button>Detail</button></a>
            </p>
        </c:if>
    </c:forEach>

    <p><a href="/item/all">All Items</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
