<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <form action="/account-info/delivery-addresses/modify/${addressEntity.id}" method="post">
            <p>state: <input type="text" name="state" value="${addressEntity.state}"></p>
            <p>city: <input type="text" name="city" value="${addressEntity.city}"></p>
            <p>street: <input type="text" name="street" value="${addressEntity.street}"></p>
            <p>apt number: <input type="text" name="aptNumber" value="${addressEntity.aptNumber}"></p>
            <p>zipCode: <input type="text" name="zipCode" value="${addressEntity.zipCode}"></p>
            <input type="submit" value="submit">
        </form>

        <p><a href="/account-info/delivery-addresses/new">add a new delivery addresses</a></p>
        <p><a href="/account-info/delivery-addresses">delivery addresses</a></p>
        <p><a href="/account-info/basic">account-info-basic</a></p>
        <p><a href="/login/user/logout">logout</a></p>
    </body>
</html>