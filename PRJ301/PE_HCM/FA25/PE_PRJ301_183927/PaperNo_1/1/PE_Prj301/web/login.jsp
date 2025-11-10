<%-- 
    Document   : login
    Created on : Apr 26, 2025, 8:58:20 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <form action="MainController?action=Login" method="POST">
            User:
            <br/> <input type="text" name="txtUserID" value="" /> <br/>
            Password:
            <br/> <input type="password" name="txtPassword" value="" /> <br/>
            
            <p style="color: red">${requestScope.msg}</p>
            
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
