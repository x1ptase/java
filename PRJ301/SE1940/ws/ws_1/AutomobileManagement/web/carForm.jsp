<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String action = (request.getAttribute("car") == null) ? "create" : "edit";
    model.Car car = (model.Car) request.getAttribute("car");
%>
<html>
<head><title><%= (action.equals("create") ? "Add New Car" : "Update Car") %></title></head>
<body>
    <h2><%= (action.equals("create") ? "Add New Car" : "Update Car") %></h2>
    <form action="<%= (action.equals("create") ? "CarCreateServlet" : "CarUpdateServlet") %>" method="post">
        <input type="hidden" name="action" value="<%= action %>"/>
        CarId: <input type="text" name="carID" value="<%= (car != null ? car.getCarID() : "") %>" <%= (action.equals("edit") ? "readonly" : "") %> /><br/>
        CarName: <input type="text" name="carName" value="<%= (car != null ? car.getCarName() : "") %>"/><br/>
        Manufacturer: <input type="text" name="manufacturer" value="<%= (car != null ? car.getManufacturer() : "") %>"/><br/>
        Price: <input type="text" name="price" value="<%= (car != null ? car.getPrice() : "") %>"/><br/>
        ReleasedYear: <input type="text" name="releasedYear" value="<%= (car != null ? car.getReleasedYear() : "") %>"/><br/><br/>
        <input type="submit" value="<%= (action.equals("create") ? "Create" : "Save") %>"/>
        <a href="CarListServlet">Back to List</a>
    </form>
</body>
</html>