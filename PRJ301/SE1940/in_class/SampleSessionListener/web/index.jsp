<%-- 
    Document   : index
    Created on : Oct 25, 2025, 4:05:25 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome to Website</h1>
        <% int count=(int)getServletContext().getAttribute("COUNT"); %>
        <h3>Number of visits: <%=count%> times</h3>
    </body>
</html>
