<%-- 
    Document   : index
    Created on : Oct 20, 2025, 11:40:10 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String msg=(String)request.getAttribute("msg");
        %>
        
        
        <form action="login" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" />
        </form>
        
        <% if(msg != null){ %>
            <%= msg %>
        <% } %>
        
    </body>
</html>
