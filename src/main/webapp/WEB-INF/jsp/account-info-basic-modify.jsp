<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <form action="/account-info/basic/modify" method="post">
          <p>nick name: <input type="text" name="nickName" value="${userInfoBasic.nickName}"></p>
          <p>first name: <input type="text" name="firstName" value="${userInfoBasic.firstName}"></p>
          <p>middle name: <input type="text" name="middleName" value="${userInfoBasic.middleName}"></p>
          <p>last name: <input type="text" name="lastName" value="${userInfoBasic.lastName}"></p>
          <p>email address: <input type="text" name="emailAddress" value="${userInfoBasic.emailAddress}"></p>
          <p>phone number: <input type="text" name="phoneNumber" value="${userInfoBasic.phoneNumber}"></p>
          <input type="submit" value="submit">
        </form>

        <form action="/account-info/basic/modify">
            <input type="submit" value="modify basic info" />
        </form>

        <p><a href="/account-info/delivery-addresses">delivery addresses</a></p>
        <p><a href="/account-info/basic">account-info-basic</a></p>
        <p><a href="/login/user/logout">logout</a></p>
    </body>
</html>