<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    <p><a href="MainController?action=createNew">Create New</a></p>

    <table border="1">
        <thead>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Product> products=(List<Product>) request.getAttribute("products");
            if(products != null && !products.isEmpty()){
                for(Product product : products){
        %>
            <tr>
                <td><%= product.getProductID() %></td>
                <td><%= product.getProductName() %></td>
                <td><%= product.getUnitPrice() %></td>
                <td><%= product.getQuantity() %></td>
                <td>
                    <a href="MainController?action=viewDetail&productId=<%= product.getProductID() %>">Edit | Details</a>
                </td>
            </tr>
        <%
                }
            } else{
        %>
            <tr>
                <td colspan="5">No products found</td>
            </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>
