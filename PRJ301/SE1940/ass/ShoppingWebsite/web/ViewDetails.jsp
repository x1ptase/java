<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ProductDTO, model.AccountDTO"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Details</title>
</head>
<body>
    <%
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        
        // Retrieve ProductDTO object from request scope
        ProductDTO product = (ProductDTO) request.getAttribute("PRODUCT_DETAIL");
        if (product == null) {
            response.sendRedirect("MainController?action=ViewProducts&msg=Error: Product not found.");
            return;
        }
    %>
    
    <h1>PRODUCT DETAILS: <%= product.getProductName() %></h1>
    
    <p><strong>Product ID:</strong> <%= product.getProductID() %></p>
    <p><strong>Product Name:</strong> <%= product.getProductName() %></p>
    <p><strong>Unit Price:</strong> $<%= String.format("%.2f", product.getUnitPrice()) %></p>
    <p><strong>Quantity Per Unit:</strong> <%= product.getQuantityPerUnit() %></p>
    <p><strong>Image:</strong> <img src="<%= product.getProductImage() %>" alt="Product Image" style="max-width: 300px;"></p>
    
    <% if (account != null && account.isType()) { %>
        <p style="color: blue;">(Technical Info) Supplier ID: <%= product.getSupplierID() %>, Category ID: <%= product.getCategoryID() %></p>
    <% } %>
    
    <hr>
    
    <% if (account != null && account.isType()) { %>
        <p><a href="MainController?action=ViewProducts">Back to Product Management</a></p>
    <% } else { %>
        <p><a href="MainController?action=ViewAllPizzas">Back to Shopping</a></p>
        <p><a href="MainController?action=AddToCart&productID=<%= product.getProductID() %>">🛒 Add to Cart</a></p>
    <% } %>
</body>
</html>