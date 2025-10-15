<%-- 
    Document   : AddNewProduct
    Created on : Oct 15, 2025, 1:13:33 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Product</title>
    </head>
    <body>
        <h1>Add New Product</h1>
        <% if(request.getAttribute("error") != null){%>
        <p style="color: red;"><%= request.getAttribute("error")%></p>
        <% } %>

        <% if(request.getAttribute("success") != null){%>
            <p style="color: green;"><%= request.getAttribute("success") %></p>
        <% } %>

        <form action="<%= request.getContextPath() %>/AddNewProductController" method="post">
            <input type="hidden" name="action" value="addProduct">
            Product Name: <input type="text" name="productName" 
                                value="<%= request.getAttribute("productName") != null ? request.getAttribute("productName") : "" %>" 
                                required><br/>
            Unit Price: <input type="number" name="unitPrice" 
                                value="<%= request.getAttribute("unitPrice") != null ? request.getAttribute("unitPrice") : "" %>" 
                                step="0.01" min="0" required><br/>
            Quantity: <input type="number" name="quantity" 
                                value="<%= request.getAttribute("quantity") != null ? request.getAttribute("quantity") : "" %>" 
                                min="0" required><br/>
            <input type="submit" value="Add Product">
            <a href="MainController?action=viewList">Back to list</a>
        </form>
    </body>
</html>
