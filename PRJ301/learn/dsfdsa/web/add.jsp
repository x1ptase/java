<%-- 
    Document   : add
    Created on : May 28, 2025, 9:52:34 AM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Member</h1>
        <form action="add" method="POST">
            Name: <input type="text" name="name" value="" /></br>
            Age: <input type="text" name="age" value="" /></br>
            <input type="submit" value="Add" />
        </form>
        <a href="list">Back to List</a>
    </body>
</html>
