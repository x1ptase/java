<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Person"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Party Members</title>
</head>
<body>
    <h1>Party Members</h1>

    <!-- Link trở về trang thêm -->
    <p><a href="add">Add New Member</a></p>

    <table border="1" cellspacing="0" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        <%
            List<Person> persons = (List<Person>) request.getAttribute("persons");
            if (persons != null && !persons.isEmpty()) {
                for (Person p : persons) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getAge() %></td>
            <td>
                <a href="edit?id=<%= p.getId() %>"><button type="button">Edit</button></a>
                <form action="delete" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= p.getId() %>">
                    <input type="submit" value="Delete" onclick="return confirm('Delete this member?');">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">No members found</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
