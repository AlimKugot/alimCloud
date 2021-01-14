<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: alim
  Date: 13.01.2021
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users in findy</title>
</head>
<body>
    <h1>Already registered</h1>
    <% List<User> userList = (ArrayList<User>) request.getAttribute("usersFromServer");%>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
        </tr>
        <% for (User user : userList) {%>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUserName()%></td>
                <td><%=user.getEmail()%></td>
            </tr>
        <%}%>
    </table>
</body>
</html>
