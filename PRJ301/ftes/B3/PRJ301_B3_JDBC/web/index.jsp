<%-- 
    Document   : index
    Created on : Oct 23, 2025, 4:39:02 AM
    Author     : x1pta
--%>
<%@page import="model.Student" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>FullName</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="i" items="${requestScope.listStd}">
                <tr>
                    <td>${i.getFullname()}</td>
                    <td>${i.getGender()}</td>
                    <td>${i.getDob()}</td>
                    <td>${i.getEmail()}</td>
                    <td>${i.getPhone()}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
