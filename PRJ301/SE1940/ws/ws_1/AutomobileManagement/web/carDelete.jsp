<%@ page contentType="text/html;charset=UTF-8" %>
<%
    model.Car car = (model.Car) request.getAttribute("car");
%>
<html>
<head><title>Delete Car</title></head>
<body>
    <h2>Delete</h2>
    <p>Are you sure you want to delete this?</p>
    <table border="1">
        <tr><th>CarId</th><td><%= car.getCarID() %></td></tr>
        <tr><th>CarName</th><td><%= car.getCarName() %></td></tr>
        <tr><th>Manufacturer</th><td><%= car.getManufacturer() %></td></tr>
        <tr><th>Price</th><td><%= car.getPrice() %></td></tr>
        <tr><th>ReleasedYear</th><td><%= car.getReleasedYear() %></td></tr>
    </table>
    <form action="CarController" method="post" style="display:inline;">
        <input type="hidden" name="action" value="delete"/>
        <input type="hidden" name="carID" value="<%= car.getCarID() %>"/>
        <input type="submit" value="Delete" style="color:red"/>
    </form>
    <a href="CarListServlet">Back to List</a>
</body>
</html>