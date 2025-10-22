<%-- 
    Document   : register
    Created on : Oct 23, 2025, 2:15:30 AM
    Author     : x1pta
--%>

<%@page import="containt.NameAttributeContaint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Sign up account !</h1>
        <form action="process" method="POST">
            Fullname <input type="text" name="txtFullname" value="" /><br/>
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            Email <input type="text" name="txtEmail" value="" /><br/>
            <input type="submit" value="Submit" />
        </form>
        
        <% String strMsg=(String)request.getAttribute(NameAttributeContaint.ERROR_ATRIBUTE); %>
        <% if(strMsg != null) {%>
            <%= strMsg %>
        <% }%>
    </body>
</html>
