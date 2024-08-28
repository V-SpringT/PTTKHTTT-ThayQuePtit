<form action="QueProject/reset-password" method="post">
    <input type="hidden" name="token" value="${param.token}">
    <div>
        <label for="password">New Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    <button type="submit">Reset Password</button>
</form>
<c:if test="${not empty message}">
    <p>${message}</p>
</c:if>
