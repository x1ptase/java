<%-- 
    Document   : BookDetail
    Created on : Oct 8, 2025, 4:01:41 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Detail</title>
    </head>
    <body>
        <h1>Book Detail</h1>
        <%--<jsp:useBean id="book" class="Models.BookBean" scope="session"/>
        <h4>BookID:<jsp:getProperty name="book" property="id"/></h4></>
        <h4>BookTitle:<jsp:getProperty name="book" property="title"/></h4>
        <h4>Quantity:<jsp:getProperty name="book" property="quantity"/></h4>
        <h4>Price:<jsp:getProperty name="book" property="price"/></h4>
        <h4>Total:${book.quantity*book.price}</h4>--%>
        
        <h4>BookID:${sessionScope.book.id}</h4></>
        <h4>BookTitle:${sessionScope.book.title}</h4>
        <h4>Quantity:${sessionScope.book.quantity}</h4>
        <h4>Price:${sessionScope.book.price}</h4>
        <h4>Total:${sessionScope.book.price*sessionScope.book.quantity}</h4>
    </body>
</html>
