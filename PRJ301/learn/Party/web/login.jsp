<%-- 
    Document   : login
    Created on : Sep 22, 2025, 2:42:53 PM
    Author     : x1pta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <c:if test="${not empty error}">
            <p style="color: red">${error}</p>
        </c:if>
        <form action="login" method="POST">
            Username: <input type="text" name="username" value="" /></br>
            Password: <input style="margin: 10px 0 10px 4px" type="password" name="password" value="" /></br>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
