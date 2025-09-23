<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Party Members</title>
    <style>
        table { border-collapse: collapse; width: 70%; margin: 20px auto; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #f0f0f0; }
        .actions form { display: inline; }
    </style>
</head>
<body>
    <h2 style="text-align:center">Party Members</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="p" items="${persons}">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.age}</td>
                <td class="actions">
                    <a href="edit?id=${p.id}">Edit</a>
                    |
                    <form action="delete" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="submit" value="Delete"
                               onclick="return confirm('Are you sure you want to delete this person?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div style="text-align:center; margin-top:20px;">
        <a href="add.jsp">Add New Member</a>
    </div>
</body>
</html>
