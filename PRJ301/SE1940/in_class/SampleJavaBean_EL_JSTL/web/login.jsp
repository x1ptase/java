<%-- 
    Document   : login
    Created on : Oct 15, 2025, 4:08:12 PM
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
        <h1>Login to website</h1>
        <form action="login-process.jsp" method="POST">
            UserName:<input type="text" name="userName" value="" /><br/>
            Password:<input type="password" name="password" value="" /><br/>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
