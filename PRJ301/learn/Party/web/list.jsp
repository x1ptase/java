<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Party Members</title>
</head>
<body>
    <h2>Party Members</h2>
    <table border="1">
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
      
                    <form action="delete" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="submit" value="Delete"
                               onclick="return confirm('Are you sure you want to delete this person?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="add.jsp">Add New Member</a>
</body>
</html>
