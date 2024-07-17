<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="js/login.js"></script>
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form name="loginForm" action="login" method="post" onsubmit="return validateForm();">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username"
                       value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>">
                <% if (request.getAttribute("usernameError") != null) { %>
                    <div class="error">
                        <%= request.getAttribute("usernameError") %>
                    </div>
                <% } %>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password"
                       value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>">
                <% if (request.getAttribute("passwordError") != null) { %>
                    <div class="error">
                        <%= request.getAttribute("passwordError") %>
                    </div>
                <% } %>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
            <% if (request.getAttribute("loginError") != null) { %>
                <div class="error message">
                    <%= request.getAttribute("loginError") %>
                </div>
            <% } %>
        </form>
        <div class="create-account-link">
            <p>Don't have an account? <a href="register.jsp">Create account</a></p>
        </div>
    </div>
</body>
</html>
