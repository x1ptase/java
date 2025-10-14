<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New Product</title>
</head>
<body>
    <h1>Add New Product</h1>
    <%
    String error=(String) request.getAttribute("error");
    String message=(String) request.getAttribute("message");
    if(error != null){
    %>
    <div style="color:red"><%= error %></div>
    <%
    }
    if(message != null){
    %>
    <div style="color:green"><%= message %></div>
    <%
    }
    %>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="addProduct"/>

        <div>
            <label for="productName">ProductName:</label>
            <input type="text" id="productName" name="productName" required/>
        </div>
        <div>
            <label for="unitPrice">UnitPrice:</label>
            <input type="number" id="unitPrice" name="unitPrice" step="0.01" min="0" required/>
        </div>
        <div>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="0" required/>
        </div>
        <button type="submit">Create</button>
    </form>
    <p><a href="MainController?action=viewList">Back to List</a></p>
</body>
</html>
