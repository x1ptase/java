<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Details</title>
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
        
        <c:if test="${not empty product}">
            <div class="product-details">
                <div class="detail-row">
                    <span class="detail-label">ProductID:</span>
                    <span class="detail-value">${product.productID}</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">ProductName:</span>
                    <span class="detail-value">${product.productName}</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">UnitPrice:</span>
                    <span class="detail-value">$${product.unitPrice}</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">Quantity:</span>
                    <span class="detail-value">${product.quantity}</span>
                </div>
            </div>
            
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="updateProduct">
                <input type="hidden" name="productId" value="${product.productID}">
                
                <div class="form-group">
                    <label for="productName">Product Name:</label>
                    <input type="text" id="productName" name="productName" value="${product.productName}" required>
                </div>
                
                <div class="form-group">
                    <label for="unitPrice">Unit Price:</label>
                    <input type="number" id="unitPrice" name="unitPrice" value="${product.unitPrice}" step="0.01" min="0" required>
                </div>
                
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" value="${product.quantity}" min="0" required>
                </div>
                
                <div class="button-group">
                    <button type="submit" class="btn btn-primary">Update Product</button>
                    <a href="MainController?action=viewList" class="btn btn-secondary">Back to List</a>
                </div>
            </form>
        </c:if>
        
        <c:if test="${empty product}">
            <div class="error">Product not found</div>
            <div class="button-group">
                <a href="MainController?action=viewList" class="btn btn-secondary">Back to List</a>
            </div>
        </c:if>
    </div>
</body>
</html>
