<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

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
            AccountDto= loginUser (AccountDto) session.getAttribute("LOGIN_USER");
            if(loginUser== null) loginUser=new AccountDto();
        %>
        User ID:<%= loginUser.getUserID() %> </br>
        Full Name:<%= loginUser.getFullName()%> </br>
        Role ID:<%= loginUser.getRoleID()%> </br>
        Password:<%= loginUser.getPassword()%>
        
    </body>
</html>
