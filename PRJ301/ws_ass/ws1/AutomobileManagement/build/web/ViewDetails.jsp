<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Details</title>
    </head>
    <body>
        <h1>Car Details</h1>
        <%
            models.CarsDTO car = (models.CarsDTO) request.getAttribute("car");
        %>
        <table border="1" cellpadding="5">
            <tr><th>CarId</th><td><%= car.getCarID() %></td></tr>
            <tr><th>CarName</th><td><%= car.getCarName() %></td></tr>
            <tr><th>Manufacturer</th><td><%= car.getManufacturer() %></td></tr>
            <tr><th>Price</th><td><%= car.getPrice() %></td></tr>
            <tr><th>ReleasedYear</th><td><%= car.getReleasedYear() %></td></tr>
        </table>
        <a href="EditCarsController?id=<%= car.getCarID() %>">Edit</a> |
        <a href="CarsListController">Back to List</a>
    </body>
</html>