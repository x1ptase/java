<%-- 
    Document   : UpdateProduct
    Created on : Nov 4, 2025
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Cần import DTO để sử dụng (Nếu không dùng EL): --%>
<%@page import="model.ProductDTO"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        
        <h1>Update Product</h1>
        <p><a href="${pageContext.request.contextPath}/MainController?action=ViewProduct">Back to Product Management</a></p>
        
        <p style="color: red;">${requestScope.msg}</p>

        <%-- Lấy đối tượng sản phẩm để pre-populate dữ liệu --%>
        <jsp:useBean id="product" scope="request" class="model.ProductDTO" /> 
        <jsp:setProperty name="product" property="*" />
        
        <form action="${pageContext.request.contextPath}/MainController" method="POST">
            
            <%-- Hidden field để lưu Product ID (Không hiển thị nhưng cần cho update) --%>
            <input type="hidden" name="txtProductID" value="${requestScope.PRODUCT_EDIT.productID}" />
            
            <label for="txtProductName">Product Name:</label> 
            <input type="text" id="txtProductName" name="txtProductName" value="${requestScope.PRODUCT_EDIT.productName}" required /><br/><br/>
            
            <label for="txtUnitPrice">Price ($):</label> 
            <input type="number" id="txtUnitPrice" name="txtUnitPrice" step="0.01" value="${requestScope.PRODUCT_EDIT.unitPrice}" required /><br/><br/>
            
            <label for="txtQuantityPerUnit">Quantity Per Unit:</label>
            <input type="text" id="txtQuantityPerUnit" name="txtQuantityPerUnit" value="${requestScope.PRODUCT_EDIT.quantityPerUnit}" required /><br/><br/>
            
            <label for="txtProductImage">Image URL (link):</label> 
            <input type="text" id="txtProductImage" name="txtProductImage" value="${requestScope.PRODUCT_EDIT.productImage}" required /><br/><br/>
            
            <label for="txtSupplierID">Supplier ID:</label>
            <input type="number" id="txtSupplierID" name="txtSupplierID" value="${requestScope.PRODUCT_EDIT.supplierID}" required /><br/><br/>

            <label for="txtCategoryID">Category ID:</label>
            <input type="number" id="txtCategoryID" name="txtCategoryID" value="${requestScope.PRODUCT_EDIT.categoryID}" required /><br/><br/>

            <input type="submit" name="action" value="UpdateProduct" />
            <input type="submit" name="action" value="ViewProduct" />
        </form>
        
    </body>
</html>