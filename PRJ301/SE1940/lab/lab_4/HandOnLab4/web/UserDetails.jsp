<%-- 
    Document   : UserDetails
    Created on : Sep 30, 2025, 10:25:27 PM
    Author     : x1pta
--%>
<%@page import="Models.DTO.User" %>
<%@page import="Models.DAO.UserDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
    </head>
    <body>
            <%
            User userLoggedIn=(User)session.getAttribute("userLoggedIn");
            if(userLoggedIn == null){
                response.sendRedirect("Login.html");
            }
            String searchValue=request.getParameter("txtSearchValue");
            User user =(User)request.getAttribute("userDetails");
            if(user != null){
            %>
            <h1>User Details</h1>
            <form action="UserController" method="POST">
                Username <input type="text" name="txtUserName" value="<%=user.getUserName()%>" /><br/>
                Password <input type="password" name="txtPassword" value="<%=user.getPassword()%>" /><br/>
                LastName <input type="text" name="txtLastName" value="<%=user.getLastName()%>" /><br/>
                <input type="hidden" name="txtSearchValue" value="<%=searchValue%>" />
                <%if(userLoggedIn.isIsAdmin() == true){ %>
                    <input type="checkbox" name="chkIsAdmin" <%if(user.isIsAdmin()) {%> checked <%}%> />isAdmin<br/>
                <% } %>
                <input type="submit" value="Update" name="action" /><br/>
                <%if(userLoggedIn.isIsAdmin() == true){ %>
                    <a href="UserController?action=Search&*txtSearchValue=<%=searchValue%>">Back</a><br/>
                <% }
                    else{
                %>
                    <a href="Login.html">Back</a><br/>
                <% 
                    }
                %>
            </form>
        <%
            }
        %>
    </body>
</html>
