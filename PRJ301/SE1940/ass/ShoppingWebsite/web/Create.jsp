<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Product</title>
    </head>
    <body>
        
        <h1>Create New Product</h1>
        <p><a href="${pageContext.request.contextPath}/MainController?action=ViewProduct">Back to Product Management</a></p>
        
        <p style="color: red;">${requestScope.msg}</p>

        <form action="${pageContext.request.contextPath}/MainController" method="POST">
            
            <label for="txtProductID">Product ID:</label> 
            <input type="number" id="txtProductID" name="txtProductID" required /><br/><br/>
            
            <label for="txtProductName">Product Name:</label> 
            <input type="text" id="txtProductName" name="txtProductName" required /><br/><br/>
            
            <label for="txtSupplierID">Supplier ID:</label> 
            <input type="number" id="txtSupplierID" name="txtSupplierID" required /><br/><br/>
            
            <label for="txtCategoryID">Category ID:</label> 
            <input type="number" id="txtCategoryID" name="txtCategoryID" required /><br/><br/>
            
            <label for="txtQuantityPerUnit">Quantity Per Unit:</label> 
            <input type="text" id="txtQuantityPerUnit" name="txtQuantityPerUnit" required /><br/><br/>
            
            <label for="txtUnitPrice">Unit Price ($):</label> 
            <input type="number" id="txtUnitPrice" name="txtUnitPrice" step="0.01" required /><br/><br/>
            
            <label for="txtProductImage">Image URL:</label> 
            <input type="text" id="txtProductImage" name="txtProductImage" placeholder="/ShoppingWebsite/resource/images/classic_pep.jpg hoặc images/classic_pep.jpg" required /><br/><br/>

            <input type="submit" name="action" value="CreateProduct" />
            <input type="submit" name="action" value="ViewProduct" />
        </form>
        
    </body>
</html>