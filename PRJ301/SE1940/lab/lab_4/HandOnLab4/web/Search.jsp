<%-- 
    Document   : Search
    Created on : Sep 30, 2025, 9:25:13 PM
    Author     : x1pta
--%>
<%@page import="Models.DTO.User" %>
<%@page import="Models.DAO.UserDAO" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Users</title>
    </head>
    <body>
        <%
            String lastName=null, searchValue=null;
            User userLoggedIn=(User)session.getAttribute("userLoggedIn");
            if(userLoggedIn != null){
                lastName=userLoggedIn.getLastName();
            }
            searchValue=request.getParameter("txtSearchValue");
        %>    
        <h3>Welcome to <text style="color: red"><%=lastName%></text></h3>
        <h1>Search user by last name</h1>
        <form action="UserController" method="POST">
            Enter search value
            <input type="text" name="txtSearchValue" value="<%=searchValue != null ? searchValue : ""%>" /><br/>
            <input type="submit" value="Search" name="action" />
        </form>
    </body>
        <%
        
        %>  
    
</html>
