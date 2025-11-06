<%@page import="java.util.List"%>
<%@page import="model.ProductDTO"%>
<%@page import="model.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Menu</title>
    </head>
    <body>
        <%
            AccountDTO account=(AccountDTO) session.getAttribute("account");
            if(account == null){
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
                return;
            }
        %>
        <h1>PIZZA MENU</h1>
        <h2>Welcome, <%= account.getFullName()%>!</h2>
        <p><a href="<c:url value='/LogoutController'/>">Logout</a></p>
        
        <%
            List<ProductDTO> productList=(List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
            if(productList != null && !productList.isEmpty()){
                for(ProductDTO product : productList){
                    String rawImg=product.getProductImage();
                    String imgSrc=(rawImg != null && rawImg.startsWith("/"))
                            ? rawImg
                            : (request.getContextPath() + "/resource/" + (rawImg != null ? rawImg : ""));
        %>
        
        <div>
            <h3><%= product.getProductName()%></h3>
            <img src="<%= imgSrc%>" alt="<%= product.getProductName()%>">
            <p><strong>Price:</strong> $<%= String.format("%.2f", product.getUnitPrice())%></p>
            <p>Size: <%= product.getQuantityPerUnit()%></p>
            <hr>
            <c:set var="productId" value="<%= product.getProductID()%>"/>
            <a href="<c:url value='/MainController'><c:param name='action' value='ViewDetails'/><c:param name='productID' value='${productId}'/></c:url>">View Details</a> |
            <a href="<c:url value='/MainController'><c:param name='action' value='AddToCart'/><c:param name='productID' value='${productId}'/></c:url>">Add to Cart</a>
        </div>
        <%
            }
        } else {
        %>
        <p>The menu is currently empty.</p>
        <%
            }
        %>
    </body>
</html>
