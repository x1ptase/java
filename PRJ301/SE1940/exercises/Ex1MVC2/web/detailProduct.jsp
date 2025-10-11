<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Details</title>
</head>
<body>
    <h1>Product</h1>
    
    <c:if test="${not empty error}">
        <div>${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div>${message}</div>
    </c:if>
    
    <c:if test="${not empty product}">
        <div>
            <p>ProductID: ${product.productID}</p>
            <p>ProductName: ${product.productName}</p>
            <p>UnitPrice: ${product.unitPrice}</p>
            <p>Quantity: ${product.quantity}</p>
        </div>
        
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="updateProduct"/>
            <input type="hidden" name="productId" value="${product.productID}"/>
            
            <div>
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" value="${product.productName}" required/>
            </div>
            <div>
                <label for="unitPrice">Unit Price:</label>
                <input type="number" id="unitPrice" name="unitPrice" value="${product.unitPrice}" step="0.01" min="0" required/>
            </div>
            <div>
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" value="${product.quantity}" min="0" required/>
            </div>
            <button type="submit">Update Product</button>
        </form>
    </c:if>
    
    <c:if test="${empty product and empty error}">
        <p>Product not found.</p>
    </c:if>
    
    <p><a href="MainController?action=viewList">Back to List</a></p>
</body>
</html>
