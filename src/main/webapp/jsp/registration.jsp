<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 03.03.2023
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TravelAgency: Registration</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../styles/login_registration.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div class="wrapper">
    <div class="title">Registration</div>
    <form method="post" action="${pageContext.request.contextPath}/controller">
        <input id="POST-command" type="hidden" name="command" value="Registration" />
        <div class="field">
            <input type="text" name="name" required>
            <label>Name</label>
        </div>
        <div class="field">
            <input type="text" name="surname" required>
            <label>Surname</label>
        </div>
        <div class="field">
            <input type="email" name="email" required>
            <label>Email Address</label>
        </div>
        <div class="field">
            <input type="text" name="phone_number" required>
            <label>Phone Number</label>
        </div>
        <div class="field">
            <input type="text" name="date_of_birth" required>
            <label>Date of birth</label>
        </div>
        <div class="field">
            <input type="password" name="password" required>
            <label>Password</label>
        </div>
        <div class="field">
            <input type="password" name="confirm_password" required>
            <label>Confirm Password</label>
        </div>
        <div class="field">
            <input type="submit" value="Create an account">
        </div>
        <div class="signup-link">Already have an account? <a href="../jsp/login.jsp">Login</a></div>
    </form>
</div>
</body>
</html>






