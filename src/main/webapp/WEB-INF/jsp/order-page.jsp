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
      h1 {text-align: center;}
      h2 {text-align: center;}
      h3 {text-align: center;}
      p {text-align: center;}
      div {text-align: center;}
</style>
<body>
  <h2>order</h2>
  <h3>order id: ${orderEntity.id}</h3>
  <h3>total price: ${orderEntity.totalPrice}</h3>
  <c:forEach var="orderItem" items="${orderItems}">
    <p>
    item name: ${orderItem.itemEntity.name}<br>
    unit price: $${orderItem.unitPrice}<br>
    total price: $${orderItem.totalPrice}<br>
    amount: ${orderItem.amount}<br>
    </p>
  </c:forEach>

<p><a href="/store-page">store home page</a></p>

</body>
</html>