<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
	    input[type="password"]::-ms-reveal,
		input[type="password"]::-ms-clear {
		    display: none;
		}
		
		input[type="password"]::-webkit-textfield-decoration-container {
		    display: none;
		}
    
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
       
        }

        .reset-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 10px;
            color: #555;
        }

        input[type="password"] {
            width: calc(100% - 30px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="text"] {
            width: calc(100% - 30px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #333333;
            border: none;
            border-radius: 4px;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .toggle-password {
       		display: none; 
            position: absolute;
		    right: 10px; 
		    top: 35%; 
		    transform: translateY(-40%);
		    cursor: pointer;
        }

        p {
            margin-top: 20px;
            font-size: 0.9em;
        }

        .error {
            color: red;
            margin-top: 10px;
        }
        
    </style>
</head>
<body>
    <div class="reset-container">
        <h2>Reset Password</h2>
        <form id="resetForm" action="reset-password" method="post" onsubmit="return validatePasswords()">
            <input type="hidden" name="token" value="${token}">
            <label for="newPassword">New Password:</label>
            <div style="position: relative;">
                <input type="password" id="newPassword" name="newPassword" required oninput="toggleVisibility('newPassword')">
                <span class="toggle-password" onclick="togglePassword('newPassword')">👁</span>
            </div>
            <label for="confirmPassword">Confirm Password:</label>
            <div style="position: relative;">
                <input type="password" id="confirmPassword" name="confirmPassword" required oninput="toggleVisibility('confirmPassword')">
                <span class="toggle-password" onclick="togglePassword('confirmPassword')">👁</span>
            </div>
            <input type="submit" value="Reset Password">
            <div id="error-message" class="error"></div>
        </form>
        <c:if test="${not empty message}">
            <p style="color:green">${message}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
        </c:if>
    </div>

    <script>
	    function toggleVisibility(fieldId) {
	        var passwordField = document.getElementById(fieldId);
	        var passwordToggle = passwordField.nextElementSibling;
	
	        if (passwordField.value.length > 0) {
	            passwordToggle.style.display = "block"; // Hiển thị biểu tượng
	        } else {
	            passwordToggle.style.display = "none";  // Ẩn biểu tượng khi không có dữ liệu
	        }
	    }
	    function togglePassword(fieldId) {
	        var passwordField = document.getElementById(fieldId);
	        var passwordToggle = passwordField.nextElementSibling;
	
	        if (passwordField.type === "password") {
	            passwordField.type = "text";
	            passwordToggle.textContent = "🙈";
	        } else {
	            passwordField.type = "password";
	            passwordToggle.textContent = "👁";
	        }
	    }


        function validatePasswords() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var errorMessage = document.getElementById("error-message");

            var passwordPattern = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$&*]).{8,}$/;

            if (!passwordPattern.test(newPassword)) {
                errorMessage.textContent = "Password must be at least 8 characters long, contain at least one number, one uppercase letter, and one special character (@, #, $, &, *).";
                return false;
            }

            if (newPassword !== confirmPassword) {
                errorMessage.textContent = "Passwords do not match.";
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
