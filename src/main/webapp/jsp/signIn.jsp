<%--
  Created by IntelliJ IDEA.
  User: alim
  Date: 16.01.2021
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signUp.css">
    <script src="${pageContext.request.contextPath}/JS/signUpCheck.js"></script>
</head>
<body>
<div class="signupSection">
    <form method="post" class="signupForm" name="signupForm">
        <h2>Sign In</h2>
        <ul class="noBullet">
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
                <input type="submit" id="join-btn" name="join" alt="Sign In" value="Sign In">
            </li>
        </ul>
    </form>
</div>
</body>
</html>
