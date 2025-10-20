<%-- 
    Document   : view
    Created on : Oct 20, 2025, 11:51:25 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
        <%
          String name=(String) request.getAttribute("name");
        %>
        
        <%= name %>
    </body>
</html>
