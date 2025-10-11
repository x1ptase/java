<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New Product</title>
</head>
<body>
    <div class="container">
        <h1>Product</h1>
        
        <c:if test="${not empty message}">
            <div class="message success">${message}</div>
        </c:if>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        
        <div class="form-container">
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="addProduct">
                
                <div class="form-group">
                    <label for="productName">Product Name:</label>
                    <input type="text" id="productName" name="productName" required 
                           placeholder="Enter product name" maxlength="50">
                </div>
                
                <div class="form-group">
                    <label for="unitPrice">Unit Price:</label>
                    <input type="number" id="unitPrice" name="unitPrice" step="0.01" min="0" required 
                           placeholder="Enter unit price">
                </div>
                
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" min="0" required 
                           placeholder="Enter quantity">
                </div>
                
                <div class="button-group">
                    <button type="submit" class="btn btn-primary">Create</button>
                    <a href="MainController?action=viewList" class="btn btn-secondary">Back to List</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
