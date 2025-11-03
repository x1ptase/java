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
        
        <p style="color: red;">${requestScope.msg}</p>

        <%-- Lấy đối tượng sản phẩm để pre-populate dữ liệu --%>
        <jsp:useBean id="product" scope="request" class="model.ProductDTO" /> 
        <jsp:setProperty name="product" property="*" />
        
        <form action="MainController" method="POST">
            
            <%-- Hidden field để lưu Product ID (Không hiển thị nhưng cần cho update) --%>
            <input type="hidden" name="txtProductID" value="${requestScope.PRODUCT_EDIT.productID}" />
            
            <label for="txtName">Name:</label> 
            <input type="text" id="txtName" name="txtName" value="${requestScope.PRODUCT_EDIT.name}" required /><br/><br/>
            
            <label for="txtPrice">Price ($):</label> 
            <input type="number" id="txtPrice" name="txtPrice" step="0.01" value="${requestScope.PRODUCT_EDIT.price}" required /><br/><br/>
            
            <label for="txtDescription">Description:</label><br/>
            <textarea id="txtDescription" name="txtDescription" rows="4" cols="50" required>${requestScope.PRODUCT_EDIT.description}</textarea><br/><br/>
            
            <label for="txtImageUrl">Image URL (link):</label> 
            <input type="text" id="txtImageUrl" name="txtImageUrl" value="${requestScope.PRODUCT_EDIT.imageUrl}" required /><br/><br/>
            
            <label for="chkIsPizzaOfTheWeek">Is Pizza Of The Week:</label>
            <%-- Logic kiểm tra checkbox: nếu giá trị là true thì thêm thuộc tính checked --%>
            <input type="checkbox" id="chkIsPizzaOfTheWeek" name="chkIsPizzaOfTheWeek" value="true" 
                   ${requestScope.PRODUCT_EDIT.isPizzaOfTheWeek ? 'checked' : ''} /><br/><br/>

            <label for="txtCategory">Category:</label>
            <input type="text" id="txtCategory" name="txtCategory" value="${requestScope.PRODUCT_EDIT.category}" required /><br/><br/>

            <input type="submit" name="action" value="UpdateProduct" />
            <input type="submit" name="action" value="ViewProduct" />
        </form>
        
    </body>
</html>