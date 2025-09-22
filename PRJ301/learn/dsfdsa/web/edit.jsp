<%-- 
    Document   : edit
    Created on : May 28, 2025, 1:34:36 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body>
        <h1>Edit Member</h1>
        <form action="edit" method="POST">
            <input type="hidden" name="id" value="${person.id}" />
            Name: <input type="text" name="name" value="${person.name}" /></br>
            Age: <input type="text" name="age" value="${person.age}" /></br>
            <input type="submit" value="Update" />
        </form>
        <a href="list">Back to List</a>
    </body>
</html>
