<%-- 
    Document   : index
    Created on : Sep 24, 2025, 4:45:18 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <form action="LoginController" method="POST">
            Name: <input type="text" name="name" value="" /><br/>
            Password: <input type="password" name="password" value="" />
            <input type="submit" value="login" />
        </form>
    </body>
</html>
