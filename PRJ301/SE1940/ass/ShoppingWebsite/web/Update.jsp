<%@page import="model.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        
        <h1>Update Product</h1>
        <p><a href="<c:url value='/MainController'><c:param name='action' value='ViewProduct'/></c:url>">Back to Product Management</a></p>
        
        <p>${requestScope.msg}</p>

        <jsp:useBean id="product" scope="request" class="model.ProductDTO" /> 
        <jsp:setProperty name="product" property="*" />
        
        <form action="<c:url value='/MainController'/>" method="POST">
            
            <input type="hidden" name="txtProductID" value="${requestScope.PRODUCT_EDIT.productID}" />
            
            <label>Product Name:</label> 
            <input type="text" name="txtProductName" value="${requestScope.PRODUCT_EDIT.productName}" required /><br/><br/>
            
            <label>Price ($):</label> 
            <input type="number" name="txtUnitPrice" step="0.01" value="${requestScope.PRODUCT_EDIT.unitPrice}" required /><br/><br/>
            
            <label>Quantity Per Unit:</label>
            <input type="text" name="txtQuantityPerUnit" value="${requestScope.PRODUCT_EDIT.quantityPerUnit}" required /><br/><br/>
            
            <label>Image URL (link):</label> 
            <input type="text" name="txtProductImage" value="${requestScope.PRODUCT_EDIT.productImage}" required /><br/><br/>
            
            <label>Supplier ID:</label>
            <input type="number" name="txtSupplierID" value="${requestScope.PRODUCT_EDIT.supplierID}" required /><br/><br/>

            <label>Category ID:</label>
            <input type="number" name="txtCategoryID" value="${requestScope.PRODUCT_EDIT.categoryID}" required /><br/><br/>

            <input type="submit" name="action" value="UpdateProduct" />
            <input type="submit" name="action" value="ViewProduct" />
        </form>
        
    </body>
</html>