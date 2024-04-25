<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <h2>cart</h2>
  <h3>total price: ${cartEntity.totalPrice}</h3>
  <h3>num of items: ${cartEntity.numOfItems}</h3>
  <c:forEach var="cartItem" items="${cartEntity.cartItems}">
  <c:if test = "${cartItem.valid}">
    <p>
    item name: ${cartItem.itemEntity.name}<br>
    unit price: $${cartItem.unitPrice}<br>
    total price: $${cartItem.totalPrice}<br>
    amount: ${cartItem.amount}<br>
    <a href="/cart/delete/${cartItem.id}"><button>remove</button></a>
    </p>
  </c:if>
  </c:forEach>

<p><a href="/store-page">store home page</a></p>

</body>
</html>