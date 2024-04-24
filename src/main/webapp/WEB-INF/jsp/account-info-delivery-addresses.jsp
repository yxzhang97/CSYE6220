<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <c:forEach var="da" items="${deliveryAddresses}">
            <p>
                ${da.state} ${da.city} ${da.street} ${da.aptNumber} ${da.zipCode}
                <a href="/account-info/delivery-addresses/modify/${da.id}"><button>modify</button></a>
            </p>
        </c:forEach>

        <p><a href="/account-info/delivery-addresses/new">add a new delivery addresses</a></p>
        <p><a href="/account-info/delivery-addresses">delivery addresses</a></p>
        <p><a href="/account-info/basic">account-info-basic</a></p>
        <p><a href="/login/user/logout">logout</a></p>
    </body>
</html>