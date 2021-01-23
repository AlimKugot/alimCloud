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
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newLogin.scss">
    <script src="https://kit.fontawesome.com/5ec1c7fb17.js" crossorigin="anonymous"></script>
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
    <div class="footer-contacts">
        <p class="author">Developer contacts: </p>
        <div class="social">
            <a href="https://vk.com/alim_kugot">
                <i class="fab fa-vk"></i>
            </a>
            <a href="https://github.com/AlimKugot">
                <i class="fab fa-github"></i>
            </a>
            <a href="mailto:alim.filipov@gmail.com">
                <i class="fas fa-envelope"></i>
            </a>
            <a href="https://www.linkedin.com/in/alim-kugotov">
                <i class="fab fa-linkedin-in"></i>
            </a>
        </div>
    </div>
</footer>
</html>
