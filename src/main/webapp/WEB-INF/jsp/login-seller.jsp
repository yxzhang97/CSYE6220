<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Seller</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        h2 {
            color: #333;
            margin-top: 20px;
        }

        form {
            margin-top: 20px;
        }

        input[type="text"], input[type="password"], input[type="submit"] {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
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
            margin: 10px 0;
            display: inline-block;
        }

        a:hover {
            color: #09f;
        }
    </style>
</head>
<body>
    <h2>Login - Seller</h2>
    <form action="/login/seller" method="post">
        Name: <input type="text" name="name"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>

    <p><a href="/item/all">All Items</a></p>
    <p><a href="/login/seller/logout">Logout</a></p>
    <p><a href="/register/seller">Seller Register</a></p>
    <p><a href="/store-page">Store Home Page</a></p>
</body>
</html>
