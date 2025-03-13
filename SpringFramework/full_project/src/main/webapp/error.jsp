<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            padding: 50px;
            text-align: center;
        }
        .error-container {
            border: 1px solid #f5c6cb;
            background-color: #f8d7da;
            color: #721c24;
            padding: 20px;
            border-radius: 5px;
            width: 60%;
            margin: 0 auto;
        }
        h2 {
            font-size: 24px;
        }
        p {
            font-size: 18px;
        }
        a {
            text-decoration: none;
            color: #721c24;
            font-weight: bold;
            border: 1px solid #721c24;
            padding: 10px 20px;
            margin-top: 20px;
            display: inline-block;
            border-radius: 5px;
            background-color: #f5c6cb;
        }
        a:hover {
            background-color: #f1a5b4;
        }
    </style>
</head>
<body>

    <div class="error-container">
        <h2>Error Occurred</h2>
        <p>
            <c:if test="${not empty error}">
                ${error}
            </c:if>
            <c:if test="${empty error}">
                An unexpected error occurred. Please try again later.
            </c:if>
        </p>
        <a href="/login">Go back to Login</a>
    </div>

</body>
</html>
