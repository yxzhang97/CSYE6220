<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
  <h2>login-user</h2>
  <form action="/login/user" method="post">
      email address: <input type="text" name="emailAddress">
      password: <input type="text" name="password">
      <input type="submit">
  </form>
  <p><a href="/account-info/basic">account-info-basic</a></p>
  <p><a href="/login/user/logout">logout</a></p>
  <p><a href="/register/user">user register</a></p>
</body>
</html>