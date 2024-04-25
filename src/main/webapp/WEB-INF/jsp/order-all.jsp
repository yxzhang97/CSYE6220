<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <h2>orders</h2>
  <c:forEach var="order" items="${orders}">
  <c:if test = "${order.valid}">
    <p>
    order id: ${order.id}<br>
    date created: ${order.dateCreated}<br>
    total price: $${order.totalPrice}<br>
    <a href="/order/${order.id}"><button>detail</button></a>
    </p>
  </c:if>
  </c:forEach>

<p><a href="/store-page">store home page</a></p>
<p><a href="/cart/all">cart</a></p>
<p><a href="/order/all">orders</a></p>

</body>
</html>