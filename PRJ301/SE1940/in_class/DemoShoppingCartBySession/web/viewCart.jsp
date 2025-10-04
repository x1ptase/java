<%-- 
    Document   : viewCart
    Created on : Oct 4, 2025, 4:06:58 PM
    Author     : x1pta
--%>
<%@page import="sample.product.ProductDTO" %>
<%@page import="sample.product.CartDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1 style="color: green">Your cart</h1>
        <%
            CartDTO cart=(CartDTO) session.getAttribute("CART");
            if(cart != null){
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Change</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
            <%
                int count=1;
                double total=0;
                for(ProductDTO p : cart.getCart().values()){
                    total += p.getPrice()*p.getQuantity();
            %>  
        <form action="MainController" method="POST">
            <tr>
                <td><%=count++%></td>
                <td><input type="text" name="id" value="<%= p.getId()%>" readonly=""/></td>
                <td><%=p.getName()%></td>
                <td><input type="number" min="1" name="quantity" value="<%=p.getQuantity()%>" /></td>
                <td><%=p.getPrice()%>$</td>
                <td><%=p.getPrice()*p.getQuantity()%></td>
                <td><input type="submit" value="Change" name="action" /></td>
                <td><input type="submit" value="Remove" name="action" /></td>
            </tr>
        </form>
            <%
                } // end for
            %>
            </tbody> 
        </table>
        <h1 style="color: blue">Total:<%=total%></h1>
        <%  
            }
        %>
        </br>
        <a href="viewProduct.jsp">Continue shopping</a>
    </body>
</html>
