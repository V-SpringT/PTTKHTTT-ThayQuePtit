<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="/QueProject/login" method="post">
        <label for="username">Username:</label>	
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
    <%
        if (request.getAttribute("	") != null) {
            out.print("<p style='color:red'>" + request.getAttribute("errorMessage") + "</p>");
        }
    %>
</body>
</html>
