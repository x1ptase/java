<%-- 
    Document   : Login
    Created on : Nov 2, 2025, 11:25:00 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login!</h1>
        <p style="color: red;">${requestScope.message}</p>
        
        <form action="LoginController" method="POST">
            Username <input type="text" name="txtUsername" /><br/>
            Password <input type="password" name="txtPassword" /><br/>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
