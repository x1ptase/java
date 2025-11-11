<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page import="pe.model.UserDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <!--your code here-->
        <%
            UserDto user=(UserDto) session.getAttribute("user");
            if(user == null){
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        %>
        <h2>Welcome <%= user.getFullName()%>! </h2>
        
        <a href="MainController?action=Search">Search by university name</a> | <a href="MainController?action=Logout">Logout</a>
    </body>
</html>
