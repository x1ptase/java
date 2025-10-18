<%-- 
    Document   : Login
    Created on : Oct 18, 2025, 4:07:20 PM
    Author     : x1pta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login to website</h1>
        <form action="LoginController" method="POST">
            Username <input type="text" name="UserName" value="" /><br/>
            Password <input type="password" name="Password" value="" /><br/>
            <input type="submit" value="Login" name="action" />
            <input type="reset" value="Reset" /><br/>
            <a href='CreateUser.jsp'>Click here to Sign up</a><br/>
        </form>
    <c:set var="message" value="${requestScope.message}"/>
    <c:if test="${message != null}">
        <text style="color: red">${message}</text>
    </c:if>
    </body>
</html>
