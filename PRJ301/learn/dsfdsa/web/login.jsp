<%-- 
    Document   : login
    Created on : May 27, 2025, 9:11:46 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            Password: <input type="password" name="password" value="" /></br>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
