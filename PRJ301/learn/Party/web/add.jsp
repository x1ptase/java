<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Member</title>
</head>
<body>
    <h1>Add New Member</h1>
    <form action="add" method="post">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" required><br><br>

        <label for="age">Age:</label><br>
        <input type="number" id="age" name="age" required><br><br>

        <input type="submit" value="Add Member">
    </form>

    <p><a href="list">Back to list</a></p>
</body>
</html>
