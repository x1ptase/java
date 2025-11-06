<%@page import="model.ProductDTO"%>
<%@page import="model.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <h1>Product Details: <%= product.getProductName() %></h1>

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
        <p><strong>Image:</strong> <img src="<%= imgSrc %>" alt="Product Image"></p>

        <% if(account != null && account.isType()){ %>
            <p>Supplier ID: <%= product.getSupplierID() %>, Category ID: <%= product.getCategoryID() %></p>
        <% } %>

        <hr>

        <% if(account != null && account.isType()){ %>
            <p><a href="<c:url value='/MainController'><c:param name='action' value='ViewProduct'/></c:url>">Back to Product Management</a></p>
        <% } else{ %>
            <p><a href="<c:url value='/MainController'><c:param name='action' value='ViewAllPizzas'/></c:url>">Back to Shopping</a></p>
        <% 
            } 
        %>
    </body>
</html>