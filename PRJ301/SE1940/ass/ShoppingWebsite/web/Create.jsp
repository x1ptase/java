<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Product</title>
    </head>
    <body>
        
        <h1>Create New Product</h1>
        <p><a href="<c:url value='/MainController'><c:param name='action' value='ViewProduct'/></c:url>">Back to Product Management</a></p>
        
        <p>${requestScope.msg}</p>

        <form action="<c:url value='/MainController'/>" method="POST">
            
            <label>Product ID:</label> 
            <input type="number" name="txtProductID" required /><br/><br/>
            
            <label>Product Name:</label> 
            <input type="text" name="txtProductName" required /><br/><br/>
            
            <label>Supplier ID:</label> 
            <input type="number" name="txtSupplierID" required /><br/><br/>
            
            <label>Category ID:</label> 
            <input type="number" name="txtCategoryID" required /><br/><br/>
            
            <label>Quantity Per Unit:</label> 
            <input type="text" name="txtQuantityPerUnit" required /><br/><br/>
            
            <label>Unit Price ($):</label> 
            <input type="number" name="txtUnitPrice" step="0.01" required /><br/><br/>
            
            <label>Image URL:</label> 
            <input type="text" name="txtProductImage" placeholder="images/classic_pep.jpg" required /><br/><br/>

            <input type="submit" name="action" value="CreateProduct" />
            <input type="submit" name="action" value="ViewProduct" />
        </form>
        
    </body>
</html>