<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Member</title>
</head>
<body>
    <h2 style="text-align:center;">Add New Member</h2>

    <form action="add" method="post" style="width:300px; margin:0 auto;">
        <label>Name:</label><br>
        <input type="text" name="name" required><br><br>

        <label>Age:</label><br>
        <input type="number" name="age" min="0" required><br><br>

        <input type="submit" value="Add">
        <a href="list">Cancel</a>
    </form>
</body>
</html>
