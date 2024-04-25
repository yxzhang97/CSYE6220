<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
    <style>
          body {text-align: center;}
    </style>
<body>
  <h2>order placed</h2>
  <h3>order id: ${orderEntity.id}</h3>
  <h3>order total price: ${orderEntity.totalPrice}</h3>
  <h3>num of items: ${orderEntity.numOfItems}</h3>
  <h3>delivery to: ${deliveryAddress.state} ${deliveryAddress.city} ${deliveryAddress.street} ${deliveryAddress.aptNumber} ${deliveryAddress.zipCode}
  <p>items:</p>
  <c:forEach var="orderItem" items="${orderItems}">
  <c:if test = "${orderItem.valid}">
    <p>
    item name: ${orderItem.itemEntity.name}<br>
    unit price: $${orderItem.unitPrice}<br>
    total price: $${orderItem.totalPrice}<br>
    amount: ${orderItem.amount}<br>
    </p>
  </c:if>
  </c:forEach>

<p><a href="/store-page">store home page</a></p>
<p><a href="/cart/all">cart</a></p>
<p><a href="/order/all">orders</a></p>

</body>
</html>