<%@ page contentType="text/html;charset=UTF-8" %>
<%
    model.Car car = (model.Car) request.getAttribute("car");
%>
<html>
<head><title>Car Details</title></head>
<body>
    <h2>Car Details</h2>
    <table border="1" cellpadding="5">
        <tr><th>CarId</th><td><%= car.getCarID() %></td></tr>
        <tr><th>CarName</th><td><%= car.getCarName() %></td></tr>
        <tr><th>Manufacturer</th><td><%= car.getManufacturer() %></td></tr>
        <tr><th>Price</th><td><%= car.getPrice() %></td></tr>
        <tr><th>ReleasedYear</th><td><%= car.getReleasedYear() %></td></tr>
    </table>
    <a href="CarEditServlet?id=<%= car.getCarID() %>">Edit</a> |
    <a href="CarListServlet">Back to List</a>
</body>
</html>