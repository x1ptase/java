<%-- 
    Document   : list
    Created on : May 27, 2025, 9:20:40 PM
    Author     : hiepn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Party Members</title>
    </head>
    <body>
        <h1>Party Members</h1>
        <a href="add">Add New Member</a>
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="person" items="${persons}">
                    <tr>
                        <td>${person.name}</td>
                        <td>${person.age}</td>
                        <td>
                            <a href="edit?id=${person.id}"><button>Edit</button></a>
                            <form action="delete" method="POST">
                                <input type="hidden" name="id" value="${person.id}" />
                                <input type="submit" value="Delete" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
