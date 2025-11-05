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
        AccountDTO account=(AccountDTO) session.getAttribute("account");
        
        ProductDTO product=(ProductDTO) request.getAttribute("PRODUCT_DETAIL");
        if(product == null){
            response.sendRedirect("MainController?action=ViewProducts&msg=Error: Product not found.");
            return;
        }
    %>
    
    <h1>PRODUCT DETAILS: <%= product.getProductName() %></h1>
    
    <p><strong>Product ID:</strong> <%= product.getProductID() %></p>
    <p><strong>Product Name:</strong> <%= product.getProductName() %></p>
    <p><strong>Unit Price:</strong> $<%= String.format("%.2f", product.getUnitPrice()) %></p>
    <p><strong>Quantity Per Unit:</strong> <%= product.getQuantityPerUnit() %></p>
    <%
        String rawImg = product.getProductImage();
        String imgSrc = (rawImg != null && rawImg.startsWith("/"))
                ? rawImg
                : (request.getContextPath()+"/resource/"+ (rawImg != null ? rawImg : ""));
    %>
    <p><strong>Image:</strong> <img src="<%= imgSrc %>" alt="Product Image" style="max-width: 300px;"></p>
    
    <% if (account != null && account.isType()) { %>
        <p style="color: green;">(Info) Supplier ID: <%= product.getSupplierID() %>, Category ID: <%= product.getCategoryID() %></p>
    <% } %>
    
    <hr>
    
    <% if (account != null && account.isType()) { %>
        <p><a href="${pageContext.request.contextPath}/MainController?action=ViewProduct">Back to Product Management</a></p>
    <% } else { %>
        <p><a href="${pageContext.request.contextPath}/MainController?action=ViewAllPizzas">Back to Shopping</a></p>
        <p><a href="${pageContext.request.contextPath}/MainController?action=AddToCart&productID=<%= product.getProductID() %>">🛒 Add to Cart</a></p>
    <% } %>
</body>
</html>