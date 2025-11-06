<%@page import="java.util.List"%>
<%@page import="model.ProductDTO"%>
<%@page import="model.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management (Admin)</title>
    </head>
    <body>
        <%
            AccountDTO account=(AccountDTO) session.getAttribute("account");
            if(account == null || !account.isType()){
                response.sendRedirect(request.getContextPath()+"/Login.jsp");
                return;
            }
        %>

        <h1>Product Management</h1>
        <p>${requestScope.msg}</p>

        <p>
            <a href="<c:url value='/MainController'><c:param name='action' value='CreatePage'/></c:url>">Create New Product</a>
            | 
            <a href="<c:url value='/LogoutController'/>">Logout</a>
        </p>

        <%
            List<ProductDTO> productList=(List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
            if(productList != null && !productList.isEmpty()){
        %>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Unit Price</th>
                        <th>Quantity Per Unit</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(ProductDTO product : productList){ %>
                    <tr>
                        <td><%= product.getProductID() %></td>
                        <td><%= product.getProductName() %></td>
                        <td>$<%= String.format("%.2f", product.getUnitPrice()) %></td>
                        <td><%= product.getQuantityPerUnit() %></td>
                        <td>
                            <c:set var="productId" value="<%= product.getProductID() %>"/>
                            <a href="<c:url value='/MainController'><c:param name='action' value='ViewDetails'/><c:param name='productID' value='${productId}'/></c:url>">Details</a> |
                            <a href="<c:url value='/MainController'><c:param name='action' value='UpdatePage'/><c:param name='productID' value='${productId}'/></c:url>">Edit</a> |
                            <form action="<c:url value='/MainController'/>" method="POST">
                                <input type="hidden" name="action" value="DeleteProduct">
                                <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                <input type="submit" value="Delete" onclick="return confirm('Confirm deletion of: <%= product.getProductName() %>?');">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else{ %>
            <p>No products found in the database</p>
        <% 
            }
        %>
    </body>
</html>