<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Modify Images</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        h2 {
            margin-top: 20px;
        }
        img {
            margin: 10px;
        }
        input[type="file"] {
            margin-top: 10px;
        }
        input[type="submit"] {
            padding: 8px 16px;
            margin-top: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        p {
            margin-top: 10px;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Modify Images</h2>

    <c:forEach var="url" items="${itemEntity.url2media}">
       <img src="${url}" width="100" height="100" alt="Alternate Text">
    </c:forEach>

    <form method="POST" action="/item/modify/${itemEntity.id}/media" enctype="multipart/form-data">
        <input type="file" name="file" /><br/><br/>
        <input type="submit" value="Submit" />
    </form>

    <p><a href="/item/all">All Items</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
