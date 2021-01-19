<%--
  Created by IntelliJ IDEA.
  User: alim
  Date: 12.01.2021
  Time: 4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome to resty</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signUp.css">
    <script src="${pageContext.request.contextPath}/JS/signUpCheck.js"></script>
</head>
<body>
<%--    todo : merge with signUp--%>

    <div class="split left">
        <img src="${pageContext.request.contextPath}/img/image_part_002.jpeg" alt=""/>
    </div>
    <div class="split right">
        <form method="post" class="signupForm" name="signupForm">
            <h2>Sign Up</h2>
            <ul class="noBullet">
                <li>
                    <label for="username"></label>
                    <input type="text" class="inputFields" id="username" name="username" placeholder="Username" value=""
                           oninput="return userNameValidation(this.value)" required/>
                </li>
                <li>
                    <label for="email"></label>
                    <input type="email" class="inputFields" id="email" name="email" placeholder="Email" value="" required/>
                </li>
                <li>
                    <label for="password"></label>
                    <input type="password" class="inputFields" id="password" name="password" placeholder="Password" value=""
                           oninput="return passwordValidation(this.value)" required/>
                </li>
                <li id="center-btn">
                    <input type="submit" id="join-btn" name="join" alt="Join" value="Join">
                </li>
            </ul>
        </form>
    </div>
</body>
</html>
