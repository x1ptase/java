<%-- 
    Document   : DeleteProduct
    Created on : Oct 15, 2025, 1:45:00 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Product</title>
    </head>
    <body>
        <h1>Delete Product</h1>
        <% if(request.getAttribute("error") != null){%>
            <p><%= request.getAttribute("error") %></p>
        <% } %>
        
        <%
            Product product=(Product)request.getAttribute("product");
            if(product != null){
        %>
            <p><strong>Are you sure you want to delete this product?</strong></p>
            
            <div>
                <p><strong>Product ID:</strong> <%= product.getProductID() %></p>
                <p><strong>Product Name:</strong> <%= product.getProductName() %></p>
                <p><strong>Unit Price:</strong> <%= product.getUnitPrice() %></p>
                <p><strong>Quantity:</strong> <%= product.getQuantity() %></p>
            </div>
            
            <form action="DeleteProductController" method="post">
                <input type="hidden" name="action" value="deleteProduct">
                <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                
                <input type="submit" value="Delete Product">
                <a href="MainController?action=viewList">Back to list</a>
            </form>
        <% } else { %>
            <p style="color: red;">Product not found!</p>
            <a href="MainController?action=viewList">Back to list</a>
        <% } %>
    </body>
</html>
