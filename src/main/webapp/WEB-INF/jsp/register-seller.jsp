<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Seller Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0 auto;
            padding: 20px;
            background-color: #f8f8f8;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin: 0 auto;
            max-width: 300px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            width: 100%;
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
    </style>
</head>
<body>
    <h2>Seller Registration</h2>
    <form action="/register/seller" method="post">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="Register">
    </form>

        <p><a href="/item/all">All Items</a></p>
        <p><a href="/item/newItem">Add New Item</a></p>
        <p><a href="/login/seller/logout">Logout</a></p>
        <p><a href="/register/seller">Seller Register</a></p>
</body>
</html>
