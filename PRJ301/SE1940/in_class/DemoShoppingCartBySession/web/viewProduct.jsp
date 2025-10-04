<%-- 
    Document   : viewProduct
    Created on : Oct 4, 2025, 3:38:13 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
    </head>
    <body>
        <h1>Welcome to My Store</h1>
        <h4>--------Select Products--------</h4>
        <form action="MainController" method="POST">
            <select name="cmbName">
                <option value="P01-Milk-100">Milk - 100$</option>
                <option value="P02-Coffee-50">Coffee - 100$</option>
                <option value="P03-Soft drink-200">Soft drink - 200$</option>
          =     <option value="P04-Milk tea-30">Milk tea - 100$</option>
                <option value="P05-Sandwich-10">Sandwich - 10$</option>
            </select>
            <select name="cmbQuantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
          =     <option value="4">4</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="50">50</option>
            </select>
            <input type="submit" value="Add" name="action" />
            <input type="submit" value="View" name="action" />
        </form>
        <%
            String message=(String) request.getAttribute("MESSAGE");
            if(message == null){
                message="";
            }
        %>
        <%=message%>
    </body>
</html>
