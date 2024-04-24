<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>comment</title>
    </head>
    <body>
        <ul>
          <li>nick name: ${userInfoBasic.nickName}</li>
          <li>first name: ${userInfoBasic.firstName}</li>
          <li>middle name: ${userInfoBasic.middleName}</li>
          <li>last name: ${userInfoBasic.lastName}</li>
          <li>email address: ${userInfoBasic.emailAddress}</li>
          <li>phone number: ${userInfoBasic.phoneNumber}</li>
        </ul>

        <form action="/account-info/basic/modify">
            <input type="submit" value="modify basic info" />
        </form>

        <p><a href="/account-info/delivery-addresses">delivery addresses</a></p>
        <p><a href="/account-info/basic">account-info-basic</a></p>
        <p><a href="/login/user/logout">logout</a></p>
    </body>
</html>