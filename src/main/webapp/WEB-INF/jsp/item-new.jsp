<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <form action="/item/newItem" method="post">
            <p>item name: <input type="text" name="name"></p>
            <p>sku: <input type="text" name="sku"></p>
            <p>price: <input type="text" name="price"></p>
            <p>inventory: <input type="text" name="inventory"></p>
            <p>manufacturer: <input type="text" name="manufacturer"></p>
            <p>description: <input type="text" name="description"></p>
            <input type="submit" value="submit">
        </form>

        <p><a href="/item/all">item all</a></p>
        <p><a href="/item/newItem">add new item</a></p>
        <p><a href="/login/seller/logout">logout</a></p>
        <p><a href="/register/seller">seller register</a></p>
    </body>
</html>