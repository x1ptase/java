<%-- 
    Document   : index
    Created on : Oct 23, 2025, 1:43:27 AM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
        <h1>Squid Game Ss3: </h1>
        <form action="random">
            <input type="number" name="number" value="" />
            <input type="submit" value="Submit" />
        </form>
        
        <h2 style="color: red">${requestScope.message}</h2>
    </body>
</html>
