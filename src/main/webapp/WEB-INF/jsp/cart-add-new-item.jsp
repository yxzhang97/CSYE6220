<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <h2>new item added to cart</h2>
  <p>item name: ${cartItemEntity.itemEntity.name}</p>
  <p>unit price: ${cartItemEntity.unitPrice}</p>
  <p>total price: ${cartItemEntity.totalPrice}</p>
  <p>amount: ${cartItemEntity.amount}</p>

  <p><a href="/store-page">store home page</a></p>
  
</body>
</html>