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
        <jsp:useBean id="book" class="Models.BookBean" scope="session"/>
        <jsp:setProperty name="book" property="*"/>

        <h3>Book Information</h3>
        <p style="color: green">
            Id: ${book.id}, Title: ${book.title}, Quantity: ${book.quantity}, Price: ${book.price}, Total: ${book.quantity * book.price}
        </p>
        <a href="CreateBook.jsp">Back to Create</a>
    </body>
</html>
