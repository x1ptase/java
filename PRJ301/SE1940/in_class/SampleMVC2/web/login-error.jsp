<%-- 
    Document   : login-error
    Created on : Sep 24, 2025, 4:45:30 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.Entities.User" %>
        <p>You are successfully logged in!</p>
        <%
            User obj=(User)request.getAttribute("UserLoggedIn");
        %>
        <p style="color: green">Welcomed, <%=obj.getName() %> </p>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
        
    </body>
</html>
