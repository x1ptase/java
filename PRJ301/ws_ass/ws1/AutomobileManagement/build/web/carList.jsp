<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Car List</title>
</head>
<body>
    <h2>Car List</h2>
    <a href="CarCreateServlet">Create New</a>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>CarID</th>
            <th>CarName</th>
            <th>Manufacturer</th>
            <th>Price</th>
            <th>ReleasedYear</th>
            <th>Action</th>
        </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.carID}</td>
                <td>${car.carName}</td>
                <td>${car.manufacturer}</td>
                <td>${car.price}</td>
                <td>${car.releasedYear}</td>
                <td>
                    <a href="CarEditServlet?id=${car.carID}">Edit</a> |
                    <a href="CarDetailsServlet?id=${car.carID}">Details</a> |
                    <a href="CarDeleteServlet?id=${car.carID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>