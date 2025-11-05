<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.AccountDTO, java.util.List, model.ProductDTO"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Management (Admin)</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/styleindex.css">
</head>
<body>
    <%
        // 1. Authorization Check: Ensure Admin role
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        if (account == null || !account.isType()) {
            response.sendRedirect(request.getContextPath()+"/Login.jsp");
            return;
        }
    %>
    
    <h1>PRODUCT MANAGEMENT</h1>
    <p style="color: green;">${requestScope.msg}</p>

    <p>
        <a href="${pageContext.request.contextPath}/MainController?action=CreatePage">➕ Create New Product</a>
        | 
        <a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
    </p>

    <%
        List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");
        if (productList != null && !productList.isEmpty()) {
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
                <% for (ProductDTO product : productList) { %>
                <tr>
                    <td><%= product.getProductID() %></td>
                    <td><%= product.getProductName() %></td>
                    <td>$<%= String.format("%.2f", product.getUnitPrice()) %></td>
                    <td><%= product.getQuantityPerUnit() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/MainController?action=ViewDetails&productID=<%= product.getProductID() %>">Details</a> |
                        <a href="${pageContext.request.contextPath}/MainController?action=UpdatePage&productID=<%= product.getProductID() %>">Edit</a> |
                        
                        <%-- Delete Form (using POST) --%>
                        <form action="${pageContext.request.contextPath}/MainController" method="POST" style="display: inline;">
                            <input type="hidden" name="action" value="DeleteProduct">
                            <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                            <input type="submit" value="Delete" onclick="return confirm('Confirm deletion of: <%= product.getProductName() %>?');">
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No products found in the database</p>
    <% } %>
</body>
</html>