<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Order</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }

        h2 {
            margin-top: 20px;
        }

        h3 {
            margin-bottom: 10px;
        }

        select, input[type="submit"] {
            padding: 8px;
            font-size: 14px;
            margin-top: 10px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        button {
            padding: 6px 12px;
            font-size: 14px;
            cursor: pointer;
        }

        button:hover {
            background-color: #ddd;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }

        .container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h2>Your Order</h2>
    <h3>Total Price: ${cartEntity.totalPrice}</h3>
    <h3>Number of Items: ${cartEntity.numOfItems}</h3>

    <div class="container">
        <table>
            <tr>
                <th>Item Name</th>
                <th>Unit Price</th>
                <th>Total Price</th>
                <th>Amount</th>
                <th>Action</th>
            </tr>
            <c:forEach var="cartItem" items="${cartEntity.cartItems}">
                <c:if test="${cartItem.valid}">
                    <tr>
                        <td>${cartItem.itemEntity.name}</td>
                        <td>$${cartItem.unitPrice}</td>
                        <td>$${cartItem.totalPrice}</td>
                        <td>${cartItem.amount}</td>
                        <td><a href="/cart/delete/${cartItem.id}"><button>Remove</button></a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>

    <form action="/order/newOrder" method="post">
    Delivery Address:
        <select name="addressId">
            <c:if test="${defaultAddress.valid}">
                <option value="${defaultAddress.id}">${defaultAddress.state} ${defaultAddress.city} ${defaultAddress.street} ${defaultAddress.aptNumber} ${defaultAddress.zipCode} (default)</option>
            </c:if>
            <c:forEach var="address" items="${deliveryAddresses}">
                <c:if test="${address.valid}">
                    <option value="${address.id}">${address.state} ${address.city} ${address.street} ${address.aptNumber} ${address.zipCode}</option>
                </c:if>
            </c:forEach>
        </select>
        <p><input type="submit" value="Place Order"></p>
    </form>

    <p><a href="/store-page">Store Home Page</a></p>
</body>
</html>
