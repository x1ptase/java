<%-- 
    Document   : UpdateProduct
    Created on : Oct 15, 2025, 1:30:00 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <h1>Update Product</h1>
        
        <% if(request.getAttribute("error") != null){%>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
        
        <% if(request.getAttribute("success") != null){%>
            <p style="color: green;"><%= request.getAttribute("success")%></p>
        <% } %>
        
        <%
            Product product=(Product) request.getAttribute("product");
            if(product != null){
        %>
            <form action="UpdateProductController" method="post">
                <input type="hidden" name="action" value="updateProduct">
                <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                
                Product ID: <%= product.getProductID() %><br/>
                Product Name: <input type="text" name="productName" 
                                    value="<%= request.getAttribute("productName") != null ? request.getAttribute("productName") : product.getProductName() %>" 
                                    required><br/>
                Unit Price: <input type="number" name="unitPrice" 
                                   value="<%= request.getAttribute("unitPrice") != null ? request.getAttribute("unitPrice") : product.getUnitPrice() %>" 
                                   step="0.01" min="0" required><br/>
                Quantity: <input type="number" name="quantity" 
                                 value="<%= request.getAttribute("quantity") != null ? request.getAttribute("quantity") : product.getQuantity() %>" 
                                 min="0" required><br/>
                
                <input type="submit" value="Update Product">
                <a href="MainController?action=viewList">Back to list</a>
            </form>
        <% } else { %>
            <p style="color: red;">Product not found!</p>
            <a href="MainController?action=viewList">Back to list</a>
        <% } %>
    </body>
</html>
