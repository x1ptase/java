<%-- 
    Document   : CreateBook
    Created on : Oct 8, 2025, 3:50:05 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Book</title>
    </head>
    <body>
        <form action="ViewBook.jsp" method="post">
            <h1>Create Book</h1>
            <table border="1" cellpadding="1">
                <tbody>
                    <tr>
                        <td>Id</td>
                        <td><input type="text" name="id" /></td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" name="title" /></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price" /></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantity" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Create" name="btnCreate" /></td>
                        <td><input type="reset" value="Reset" name="Reset" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
