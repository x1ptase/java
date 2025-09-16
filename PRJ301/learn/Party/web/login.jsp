<%-- 
    Document   : login
    Created on : Sep 16, 2025, 3:49:04 PM
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
        <form action="login" method="POST">
            Username: <input type="text" name="username" value="" /></br>
            Password: <input style="margin-left: 4px; margin-top: 5px" type="password" name="password" value="" /></br>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
