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
  <h2>your order</h2>
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

  <form action="/order/newOrder" method="post">
    <select name="addressId">
        <c:if test = "${defaultAddress.valid}">
            <option value="${defaultAddress.id}">${defaultAddress.state} ${defaultAddress.city} ${defaultAddress.street} ${defaultAddress.aptNumber} ${defaultAddress.zipCode} (default)</option>
        </c:if>
        <c:forEach var="address" items="${deliveryAddresses}">
          <c:if test = "${address.valid}">
            <option value="${address.id}">${address.state} ${address.city} ${address.street} ${address.aptNumber} ${address.zipCode} </option>
          </c:if>
        </c:forEach>
    </select>
    <p><input type="submit" value="place order"></p>
  </form>


<p><a href="/store-page">store home page</a></p>

</body>
</html>