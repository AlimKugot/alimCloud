<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome to resty</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signUp.scss">
    <script src="${pageContext.request.contextPath}/JS/signUpCheck.js"></script>
    <script src="https://kit.fontawesome.com/5ec1c7fb17.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="left">
        <div class="header">
            <h2 class="animation a1">Welcome</h2>
            <h4 class="animation a2">Sign up into our platform</h4>
        </div>
        <form class="form" method="post">
            <label for="username">
                <input type="text" id="username" class="form-field animation a3" placeholder="Username" value=""
                       oninput="return userNameValidation(this.value)" required>
            </label>
            <label for="email">
                <input type="email" id="email" class="form-field animation a4" placeholder="Email Address" required>
            </label>
            <label for="password">
                <input type="password" id="password" class="form-field animation a5" placeholder="Password"
                       value="" oninput="return passwordValidation(this.value)" required>
            </label>
            <button type="submit" class="button animation a6">SIGN UP</button>
        </form>
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
