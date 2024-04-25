<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        h1, h2, h3 {
            color: #333;
        }

        p {
            color: #666;
        }

        form {
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #069;
        }

        a:hover {
            color: #09f;
        }
    </style>
</head>
<body>
    <h1>User Information</h1>
    <p>Nick Name: ${userInfoBasic.nickName}</p>
    <p>First Name: ${userInfoBasic.firstName}</p>
    <p>Middle Name: ${userInfoBasic.middleName}</p>
    <p>Last Name: ${userInfoBasic.lastName}</p>
    <p>Email Address: ${userInfoBasic.emailAddress}</p>
    <p>Phone Number: ${userInfoBasic.phoneNumber}</p>

    <form action="/account-info/basic/modify">
        <input type="submit" value="Modify Basic Info" />
    </form>

    <p><a href="/account-info/delivery-addresses">Delivery Addresses</a></p>
    <p><a href="/account-info/basic">Account Info Basic</a></p>
    <p><a href="/login/user/logout">Logout</a></p>
</body>
</html>
