<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.ProductDTO" %>
<html>
<body>
    <h2>Add Product</h2>
    <form action="ProductServlet" method="post">
        <input type="hidden" name="action" value="add" />
        <label>ID: <input type="number" name="id" required /></label>
        <label>Name: <input type="text" name="name" required /></label>
        <label>Price: <input type="number" step="0.01" name="unitPrice" required /></label>
        <label>Quantity: <input type="number" name="quantity" required /></label>
        <button type="submit">Add</button>
    </form>

    <h2>Product List</h2>
<%
    List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("productList");
    if (list != null) {
        for (ProductDTO p : list) {
%>
    <div style="margin-bottom:10px;">
        <p>
            ID: <%= p.getId() %> |
            Name: <%= p.getName() %> |
            Price: <%= p.getUnitPrice() %> |
            Quantity: <%= p.getQuantity() %>
        </p>
        <form action="ProductServlet" method="post" style="display:inline-block;margin-right:10px;">
            <input type="hidden" name="action" value="update" />
            <input type="hidden" name="id" value="<%= p.getId() %>" />
            <label>Name: <input type="text" name="name" value="<%= p.getName() %>" required /></label>
            <label>Price: <input type="number" step="0.01" name="unitPrice" value="<%= p.getUnitPrice() %>" required /></label>
            <label>Quantity: <input type="number" name="quantity" value="<%= p.getQuantity() %>" required /></label>
            <button type="submit">Update</button>
        </form>
        <form action="ProductServlet" method="post" style="display:inline-block;" onsubmit="return confirm('Delete product ID <%= p.getId() %>?');">
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="id" value="<%= p.getId() %>" />
            <button type="submit">Delete</button>
        </form>
    </div>
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