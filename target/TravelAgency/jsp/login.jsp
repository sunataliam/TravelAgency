<%--
  Created by IntelliJ IDEA.
  User: natal
  Date: 28.02.2023
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!DOCTYPE html>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet'>
<link href="../styles/login.css" rel="stylesheet">


<form method="post" action="${pageContext.request.contextPath}/controller">
    <input id="POST-command" type="hidden" name="command" value="Login" >
    <div class="box">
        <h1>Login</h1>

        <input type="email" name="email" value="Email" onFocus="field_focus(this, 'Email');" onblur="field_blur_email(this, 'Email');" class="email" />

        <input type="password" name="password" value="Password" onFocus="field_focus(this, 'Password');" onblur="field_blur_password(this, 'Password');" class="email" />

        <button type="submit" class="btn">Login</button>

    </div>

</form>
<p>Don't have an account? <u style="color:#A56EFF;">Click Here!</u></p>
</body>
</html>
