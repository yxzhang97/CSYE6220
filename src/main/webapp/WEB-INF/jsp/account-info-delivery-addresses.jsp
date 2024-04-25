<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delivery Addresses</title>
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
            margin-left: 10px;
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
    <c:if test="${defaultAddress.valid}">
        <p>Default Delivery Address: ${defaultAddress.state} ${defaultAddress.city} ${defaultAddress.street} ${defaultAddress.aptNumber} ${defaultAddress.zipCode}</p>
    </c:if>

    <c:forEach var="da" items="${deliveryAddresses}">
        <c:if test="${da.valid}">
            <p>
                ${da.state} ${da.city} ${da.street} ${da.aptNumber} ${da.zipCode}
                <a href="/account-info/delivery-addresses/modify/${da.id}"><button>Modify</button></a>
                <a href="/account-info/delivery-addresses/modify/default/${da.id}"><button>Set as Default</button></a>
                <a href="/account-info/delivery-addresses/remove/${da.id}"><button>Remove</button></a>
            </p>
        </c:if>
    </c:forEach>

    <p><a href="/account-info/delivery-addresses/new">Add a New Delivery Address</a></p>
    <p><a href="/account-info/delivery-addresses">Delivery Addresses</a></p>
    <p><a href="/account-info/basic">Account Info Basic</a></p>
    <p><a href="/login/user/logout">Logout</a></p>
</body>
</html>
