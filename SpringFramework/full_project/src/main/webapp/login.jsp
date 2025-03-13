<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            margin: 0;
        }

        form {
            background-color: white;
            padding: 20px;
            margin-top: 50px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin-left: auto;
            margin-right: auto;
        }

        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>

    <h1>Login</h1>

    <!-- Check if there is an error and display it -->
    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

    <!-- Form to submit the login details -->
    <form action="/login" method="get">
        <!-- Input field for the user's name -->
        <input type="text" name="name" placeholder="Enter your name" required />
        <!-- Input field for the user's phone number -->
        <input type="text" name="phone" placeholder="Enter your phone number" required />
        <!-- Submit button to trigger the form submission -->
        <button type="submit">Login</button>
    </form>

</body>
</html>
