<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <c:forEach var="item" items="${items}">
  <c:if test = "${item.valid}">
    <p>
    item name: ${item.name}
    <a href="/item/${item.id}"><button>detail</button></a>
    </p>
  </c:if>
  </c:forEach>

  <p><a href="/item/all">item all</a></p>
  <p><a href="/item/newItem">add new item</a></p>
  <p><a href="/login/seller/logout">logout</a></p>
  <p><a href="/register/seller">seller register</a></p>
</body>
</html>