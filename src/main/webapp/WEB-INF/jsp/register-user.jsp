<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
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
            max-width: 400px;
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
    <h2>User Registration</h2>
    <form action="/register/user" method="post">
        <label for="nickName">Nick Name:</label><br>
        <input type="text" id="nickName" name="nickName"><br>
        <label for="firstName">First Name:</label><br>
        <input type="text" id="firstName" name="firstName"><br>
        <label for="middleName">Middle Name:</label><br>
        <input type="text" id="middleName" name="middleName"><br>
        <label for="lastName">Last Name:</label><br>
        <input type="text" id="lastName" name="lastName"><br>
        <label for="emailAddress">Email Address:</label><br>
        <input type="text" id="emailAddress" name="emailAddress"><br>
        <label for="phoneNumber">Phone Number:</label><br>
        <input type="text" id="phoneNumber" name="phoneNumber"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
