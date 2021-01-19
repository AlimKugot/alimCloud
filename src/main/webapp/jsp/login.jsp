<%--
  Created by IntelliJ IDEA.
  User: alim
  Date: 19.01.2021
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newLogin.scss">
</head>
<body>
    <div class="container">
        <div class="left">
            <div class="header">
                <h2 class="animation a1">Welcome Back</h2>
                <h4 class="animation a2">Log in to your account using email and password</h4>
            </div>
            <div class="form">
                <label for="email">
                    <input type="email" id="email" class="form-field animation a3" placeholder="Email Address" required>
                </label>
                <label for="password">
                    <input type="password" id="password" class="form-field animation a4" placeholder="Password" required>
                </label>
                <p class="animation a5"><a href="#">Forgot Password</a></p>
                <button type="submit" class="animation a6">LOGIN</button>
            </div>
        </div>
        <div class="right"></div>
    </div>
</body>
<footer>
<%--    todo: footer--%>
</footer>
</html>
