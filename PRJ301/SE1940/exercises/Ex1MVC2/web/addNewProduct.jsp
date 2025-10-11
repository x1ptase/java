<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add New Product</title>
</head>
<body>
    <h1>Product</h1>
    
    <c:if test="${not empty error}">
        <div>${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div>${message}</div>
    </c:if>
    
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
