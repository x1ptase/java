<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Member</title>
</head>
<body>
    <h2>Add New Member</h2>
    
    <form action="add" method="post">
        <label>Name:</label><br>
        <input type="text" name="name" required><br><br>

        <label>Age:</label><br>
        <input type="number" name="age" min="0" required><br><br>

        <input type="submit" value="Add">
        <a href="list" style="margin: 0 0 0 10px">Cancel</a>
    </form>
</body>
</html>
