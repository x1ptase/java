<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars List</title>
</head>
<body>
    <h1>Cars List</h1>
    <a href="CreateCars.jsp">Create New</a>
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
                    <a href="EditCarsController?id=${car.carID}">Edit</a> |
                    <a href="ViewDetailsController?carID=${car.carID}">Details</a> |
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>