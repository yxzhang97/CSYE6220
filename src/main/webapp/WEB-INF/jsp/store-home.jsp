<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <style>
              body {text-align: center;}
        </style>
    <body>

    <p>
        <form action="/store-page/search" method="get">
            <input type="text" name="keyword">
            <input type="submit" value="search">
        </form>
    </p>

        <c:forEach var="item" items="${items}">
            <p>
                <a href="/store-page/item-detail/${item.id}">
                    <img src="${cover}" width="300" height="200" alt="cover image"></img>
                    ${item.name} $${item.price}
                </a>
            </p>
        </c:forEach>



        <p><a href="/store-page">store home page</a></p>
        <p><a href="/cart/all">cart</a></p>
        <p><a href="/order/all">orders</a></p>

    </body>
</html>
