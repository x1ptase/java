<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Person"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Member</title>
</head>
<body>
    <h1>Edit Member</h1>
    <%
        Person person = (Person) request.getAttribute("person");
    %>
    <form action="edit" method="POST">
        <input type="hidden" name="id" value="<%= person.getId() %>" />
        Name: <input type="text" name="name" value="<%= person.getName() %>" /><br>
        Age: <input type="text" name="age" value="<%= person.getAge() %>" /><br>
        <input type="submit" value="Update" />
    </form>
    <a href="list">Back to List</a>
</body>
</html>
