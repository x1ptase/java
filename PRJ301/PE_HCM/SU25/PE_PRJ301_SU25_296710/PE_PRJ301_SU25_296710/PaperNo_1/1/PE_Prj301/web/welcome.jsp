<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page import="pe.model.AccountDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <!--your code` here-->
        <%
            AccountDto account=(AccountDto) session.getAttribute("account");
            if(account == null){
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        %>
        <h2>Welcome <%= account.getFullName()%>!</h2>
        
        <a href="SearchController">Search</a> | <a href="LogoutController">Logout</a>
    </body>
</html>
