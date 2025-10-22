<%-- 
    Document   : profile
    Created on : Oct 23, 2025, 2:22:49 AM
    Author     : x1pta
--%>

<%@page import="containt.NameAttributeContaint"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    
    <%
        User usr=(User)request.getAttribute(NameAttributeContaint.USER_ATTRIBUTE);
    %>
    
    <body>
        <h1>Profile</h1>
        
        FullName: <%= usr.getFullname() %><br/>
        UserName: <%= usr.getUsername()%><br/>
        Email:    <%= usr.getEmail() %><br/>
    </body>
</html>
