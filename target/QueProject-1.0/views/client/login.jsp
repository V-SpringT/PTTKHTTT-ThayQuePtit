<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="/QueProject/login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <input type="submit" value="Login">
        </form>
        <a href="/QueProject/forgot-password">Forgot your password?</a> 
        <%
            if (request.getAttribute("errorMessage") != null) {
                out.print("<p class='error-message'>" + request.getAttribute("errorMessage") + "</p>");
            }
        %>
    </div>
</body>
</html>
