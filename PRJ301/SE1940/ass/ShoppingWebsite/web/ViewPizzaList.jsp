<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AccountDTO, java.util.List, model.ProductDTO"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pizza Menu - Shopping</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/styleindex.css">
</head>
<body>
    <%
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath()+"/Login.jsp");
            return;
        }
    %>
    
    <h1>PIZZA MENU</h1>
    <h2>Welcome, <%= account.getFullName() %>!</h2>

    <p><a href="${pageContext.request.contextPath}/LogoutController">Logout</a></p>
    
    <%
        // Retrieve Product List from Request Scope
        List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
        
        if (productList != null && !productList.isEmpty()) {
            for (ProductDTO product : productList) { 
    %>
                <div class="product-card">
                    <h3><%= product.getProductName() %></h3>
                    <%
                        String rawImg = product.getProductImage();
                        String imgSrc = (rawImg != null && rawImg.startsWith("/"))
                                ? rawImg
                                : (request.getContextPath()+"/resource/"+ (rawImg != null ? rawImg : ""));
                    %>
                    <img src="<%= imgSrc %>" alt="<%= product.getProductName() %>" style="max-width: 300px; width: 100%; height: auto; object-fit: cover; display: block;">
                    <p><strong>Price:</strong> $<%= String.format("%.2f", product.getUnitPrice()) %></p>
                    <p>Size: <%= product.getQuantityPerUnit() %></p>
                    
                    <hr>
                    <a href="${pageContext.request.contextPath}/MainController?action=ViewDetails&productID=<%= product.getProductID() %>">View Details</a> |
                    <a href="${pageContext.request.contextPath}/MainController?action=AddToCart&productID=<%= product.getProductID() %>">🛒 Add to Cart</a>
                </div>
    <% 
            }
        } else { 
    %>
        <p>The menu is currently empty.</p>
    <% } %>
    
    <div style="clear: both;"></div>
</body>
</html>