<%-- 
    Document   : ViewProductList0
    Created on : Oct 15, 2025, 1:17:50 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="model.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <h1>Product List</h1>
        
        <% if(request.getAttribute("error") != null){%>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
        
        <% if(request.getAttribute("success") != null){%>
            <p style="color: green;"><%= request.getAttribute("success") %></p>
        <% } %>
        
        <% if(request.getAttribute("searchResult") != null){%>
            <p style="color: blue;"><%= request.getAttribute("searchResult") %></p>
        <% } %>
        
        <a href="MainController?action=showAddForm">Create New</a>
       
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
                        <a href="MainController?action=viewDetail&productId=<%= product.getProductID() %>">Details</a> | 
                        <a href="UpdateProductController?action=showForm&productId=<%= product.getProductID() %>">Edit</a> | 
                        <a href="DeleteProductController?action=confirmDelete&productId=<%= product.getProductID() %>">Delete</a>
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
