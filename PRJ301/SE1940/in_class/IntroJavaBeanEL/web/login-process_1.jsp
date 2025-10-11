<%-- 
    Document   : login-process_1
    Created on : Oct 11, 2025, 3:26:48 PM
    Author     : x1pta
--%>
<%@page import="Models.LoginBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Process</title>
    </head>
    <body>
        <jsp:useBean id="objLogin" class="Models.LoginBean" scope="session"></jsp:useBean>
        <jsp:setProperty name="objLogin" property="name" value="${param.name}"/>
        <jsp:setProperty name="objLogin" property="password" value="${param.password}"/>
        <h3>User name:${sessionScope.objLogin.name}</h3>
        <h3>Password:${sessionScope.objLogin.password}</h3>
    </body>
</html>
