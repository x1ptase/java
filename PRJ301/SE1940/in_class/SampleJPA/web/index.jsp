<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.ProductDTO" %>
<html>
<body>
    <h2>Product List</h2>
<%
    List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("productList");
    if (list != null) {
        for (ProductDTO p : list) {
%>
    <p>ID: <%= p.getId() %> |
    Name: <%= p.getName() %> |
    Price: <%= p.getUnitPrice() %> |
    Quantity: <%= p.getQuantity() %>
    </p>
<%
        }
    } else {
%>
    <p>No products found.</p>
<%
    }
%>
</body>
</html>