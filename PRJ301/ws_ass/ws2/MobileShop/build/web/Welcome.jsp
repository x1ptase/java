<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mobile List</title>
    </head>
    <body>
        <h2>Mobile List</h2>
        
        <c:if test="${sessionScope.user.role == 1}">
            <a href="CreateMobiles.jsp">Create New Mobile</a> | 
        </c:if>
        <a href="LogoutController">Logout</a>
        
        <p style="color: green">${requestScope.msg}</p>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Year</th>
                <th>Quantity</th>
                <th>Not Sale</th>
                <c:if test="${sessionScope.user.role == 1}">
                    <th>Action</th>
                </c:if>
            </tr>
            <c:forEach var="m" items="${mobiles}">
                <tr>
                    <td>${m.mobileId}</td>
                    <td>${m.mobileName}</td>
                    <td>${m.description}</td>
                    <td>${m.price}</td>
                    <td>${m.yearOfProduction}</td>
                    <td>${m.quantity}</td>
                    <td><c:out value="${m.notSale ? 'Yes' : 'No'}"/></td>
                    <c:if test="${sessionScope.user.role == 1}">
                        <td>
                            <a href="UpdateMobiles.jsp?mobileId=${m.mobileId}">Update</a> | 
                            <a href="DeleteMobilesController?mobilesId=${m.mobileId}" 
                               onclick="return confirm('Are you sure you want to delete this mobile?')">Delete</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
