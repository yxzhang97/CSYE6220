<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <ul>
        <li>item name: ${itemEntity.name}</li>
        <li>sku: ${itemEntity.sku}</li>
        <li>unit price: ${itemEntity.price}</li>
        <li>inventory: ${itemEntity.inventory}</li>
        <li>manufacturer: ${itemEntity.manufacturer}</li>
        <li>description: ${itemEntity.description}</li>
        </ul>
        
        <form action="/item/modify/${itemEntity.id}">
           <input type="submit" value="modify item" />
        </form>

        <p><a href="/item/all">item all</a></p>
        <p><a href="/item/newItem">add new item</a></p>
        <p><a href="/login/seller/logout">logout</a></p>
        <p><a href="/register/seller">seller register</a></p>
    </body>
</html>