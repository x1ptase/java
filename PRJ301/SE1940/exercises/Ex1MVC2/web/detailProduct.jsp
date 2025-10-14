<%@page contentType="text/html" pageEncoding="UTF-8" import="model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Details</title>
</head>
<body>
    <h1>Product</h1>
    <%
        String error=(String) request.getAttribute("error");
        String message=(String) request.getAttribute("message");
        Product product=(Product) request.getAttribute("product");
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
        if(product != null){
    %>
        <div>
            <p><strong>ProductID:</strong> <%= product.getProductID() %></p>
            <p><strong>ProductName:</strong> <%= product.getProductName() %></p>
            <p><strong>UnitPrice:</strong> <%= product.getUnitPrice() %></p>
            <p><strong>Quantity:</strong> <%= product.getQuantity() %></p>
        </div>
    <%
        } else if(error == null){
    %>
        <p>Product not found.</p>
    <%
        }
    %>
    <p><a href="MainController?action=viewList">Back to List</a></p>
</body>
</html>
