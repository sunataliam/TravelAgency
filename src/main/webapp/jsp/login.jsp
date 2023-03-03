<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 03.03.2023
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TravelAgency: Login</title>
    <meta charset="utf-8">
    <link href="../styles/login_registration.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <div class="title">Login</div>
    <form method="post" action="${pageContext.request.contextPath}/controller">
        <input id="POST-command" type="hidden" name="command" value="Login" />
        <div class="field">
            <input type="email" name="email" required>
            <label>Email Address</label>
        </div>
        <div class="field">
            <input type="password" name="password" required>
            <label>Password</label>
        </div>
        <div class="field">
            <input type="submit" value="Login">
        </div>
        <div class="signup-link">Not a member? <a href="../jsp/registration.jsp">Create an account</a></div>
    </form>
</div>
</body>
</html>



