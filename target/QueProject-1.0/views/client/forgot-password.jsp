<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <style>
        
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .forgot-password-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        .forgot-password-container h2 {
            margin-bottom: 20px;
            text-align: center;
            color: #333;
        }

        .forgot-password-container label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        .forgot-password-container input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .forgot-password-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .forgot-password-container input[type="submit"]:hover {
            background-color: #218838;
        }

        .message {
            color: green;
            margin-top: 10px;
            text-align: center;
        }

        .error-message {
            color: red;
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="forgot-password-container">
        <h2>Forgot Password</h2>
        <form action="/QueProject/forgot-password" method="post">
            <label for="email">Enter your email address:</label>
            <input type="email" id="email" name="email" required>
            <input type="submit" value="Submit">
        </form>
        <%
            if (request.getAttribute("message") != null) {
                out.print("<p class='message'>" + request.getAttribute("message") + "</p>");
            } else if (request.getAttribute("errorMessage") != null) {
                out.print("<p class='error-message'>" + request.getAttribute("errorMessage") + "</p>");
            }
        %>
    </div>
</body>
</html>
