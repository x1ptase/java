<%-- 
    Document   : ViewBook
    Created on : Oct 8, 2025, 3:51:18 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Book</title>
    </head>
    <body>
        <h1>View Book</h1>
        <%--<jsp:scriptlet>
            String id=request.getParameter("id");
            String title=request.getParameter("title");
            int quantity=Integer.parseInt(request.getParameter("quantity"));
            double price=Double.parseDouble(request.getParameter("price"));
            double total=quantity*price;
        </jsp:scriptlet>--%>
        <jsp:useBean id="book" class="Models.BookBean" scope="session"/>
        <jsp:setProperty name="book" property="id" value="${param.id}"/>
        <jsp:setProperty name="book" property="title" value="${param.title}"/>
        <jsp:setProperty name="book" property="quantity" value="${param.quantity}"/>
        <jsp:setProperty name="book" property="price" value="${param.price}"/>
        
        <h3>Book Information</h3>
        <p style="color: red">${book},Subtotal:${book.quantity*book.price}</p>
        <a href="BookDetail.jsp">Book Details</a>
    </body>
</html>
