<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
</head>
<body>
    <div class="container">
        <h2>Add New Product</h2>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
        <% if (request.getAttribute("success") != null) { %>
            <div class="success">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        
        <form action="AddNewProductController" method="post">
            <input type="hidden" name="action" value="addProduct">
            
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" 
                       value="<%= request.getAttribute("productName") != null ? request.getAttribute("productName") : "" %>" 
                       required>
            </div>
            
            <div class="form-group">
                <label for="unitPrice">Unit Price:</label>
                <input type="number" id="unitPrice" name="unitPrice" 
                       value="<%= request.getAttribute("unitPrice") != null ? request.getAttribute("unitPrice") : "" %>" 
                       step="0.01" min="0" required>
            </div>
            
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" 
                       value="<%= request.getAttribute("quantity") != null ? request.getAttribute("quantity") : "" %>" 
                       min="0" required>
            </div>
            
            <div class="button-group">
                <input type="submit" value="Add Product">
                <a href="MainController?action=viewList" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
