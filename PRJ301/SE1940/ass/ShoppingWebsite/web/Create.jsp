<%-- 
    Document   : CreateProduct
    Created on : Nov 4, 2025
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Product</title>
        <%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/admin.css"> --%>
    </head>
    <body>
        
        <h1>Create New Product</h1>
        
        <p style="color: red;">${requestScope.msg}</p>

        <form action="MainController" method="POST">
            
            <label for="txtName">Name:</label> 
            <input type="text" id="txtName" name="txtName" required /><br/><br/>
            
            <label for="txtPrice">Price ($):</label> 
            <input type="number" id="txtPrice" name="txtPrice" step="0.01" required /><br/><br/>
            
            <label for="txtDescription">Description:</label><br/>
            <textarea id="txtDescription" name="txtDescription" rows="4" cols="50" required></textarea><br/><br/>
            
            <label for="txtImageUrl">Image URL (link):</label> 
            <input type="text" id="txtImageUrl" name="txtImageUrl" required /><br/><br/>
            
            <label for="chkIsPizzaOfTheWeek">Is Pizza Of The Week:</label>
            <input type="checkbox" id="chkIsPizzaOfTheWeek" name="chkIsPizzaOfTheWeek" value="true" /><br/><br/>

            <label for="txtCategory">Category:</label>
            <input type="text" id="txtCategory" name="txtCategory" required /><br/><br/>

            <input type="submit" name="action" value="CreateProduct" />
            <input type="submit" name="action" value="ViewProduct" />
        </form>
        
    </body>
</html>