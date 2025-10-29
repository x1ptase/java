<%-- 
    Document   : Login
    Created on : Oct 29, 2025, 1:03:55 PM
    Author     : x1pta
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
        <h1>Login to website</h1>
        <form action="login" method="POST">
            Username <input type="text" name="txtUserName" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="action" />
            <input type="reset" value="Reset" /><br/>
            <a href="CreateUser.jsp">Click here to Sign up</a><br/>
        </form>
        <c:set var="message" value="${requestScope.message}"/>
        <c:if test="${message != null}">
            <span style="color: red">${message}</span>
        </c:if>
    </body>
</html>
