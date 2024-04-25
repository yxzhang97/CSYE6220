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

    <c:forEach var="url" items="${url2medias}">
       <img src="${url}" width="100" height="100" alt="Alternate Text">
       <a href="/item/modify/${itemEntity.id}/media${url}"><button>Delete</button></a>
    </c:forEach>


    <p><a href="/item/all">All Items</a></p>
    <p><a href="/item/newItem">Add New Item</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
