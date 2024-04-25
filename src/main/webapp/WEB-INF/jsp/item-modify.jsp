<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <h2>item detail</h2>
  <form action="/item/modify/${itemEntity.id}" method="post">
      <p>item name: <input type="text" name="name" value="${itemEntity.name}"></p>
      <p>sku: <input type="text" name="sku" value="${itemEntity.sku}"></p>
      <p>unit price: <input type="text" name="price" value="${itemEntity.price}"></p>
      <p>inventory: <input type="text" name="inventory" value="${itemEntity.inventory}"></p>
      <p>manufacturer: <input type="text" name="manufacturer" value="${itemEntity.manufacturer}"></p>
      <p>description: <input type="text" name="description" value="${itemEntity.description}"></p>
      <input type="submit" value="submit">
  </form>


  <c:forEach var="url" items="${itemEntity.url2media}">
    <p><img src="${url}" alt="alternatetext"></p>
  </c:forEach>

  <c:forEach var="comment" items="${itemEntity.comments}">
    <p>date: ${comment.dateCreated}</p>
    <p>user: ${comment.userEntity.nickName}</p>
    <p> ${comment.text} </p>
    <br>
  </c:forEach>

  <p><a href="/item/all">item all</a></p>
  <p><a href="/item/newItem">add new item</a></p>
  <p><a href="/login/seller/logout">logout</a></p>
  <p><a href="/register/seller">seller register</a></p>
</body>
</html>