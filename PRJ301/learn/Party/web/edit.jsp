<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Member</title>
</head>
<body>
    <h2 style="text-align:center;">Edit Member</h2>

    <form action="edit" method="post" style="width:300px; margin:0 auto;">
        <!-- giữ ID trong hidden -->
        <input type="hidden" name="id" value="${person.id}">

        <label>Name:</label><br>
        <input type="text" name="name" value="${person.name}" required><br><br>

        <label>Age:</label><br>
        <input type="number" name="age" value="${person.age}" min="0" required><br><br>

        <input type="submit" value="Update">
        <a href="list">Cancel</a>
    </form>
</body>
</html>
