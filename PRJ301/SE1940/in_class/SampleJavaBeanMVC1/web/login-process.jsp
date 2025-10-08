<%-- 
    Document   : login-process
    Created on : Oct 8, 2025, 4:32:05 PM
    Author     : x1pta
--%>
<%@page import="ProcessBeans.UserBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Process</title>
    </head>
    <body>
        <jsp:useBean id="user" class="ProcessBeans.UserBean" scope="session"/>
        <jsp:setProperty name="user" property="userName" value="${param.userName}"/>
        <jsp:setProperty name="user" property="password" value="${param.password}"/>
        <%-- <jsp:setProperty name="user" property="*"/> --%>

        <%
            UserBean userLoggedIn = user.validate();
            if (userLoggedIn != null) {
        %>
                <jsp:forward page="login-success.jsp">
                    <jsp:param name="lastName" value="<%= userLoggedIn.getLastName() %>"/>
                </jsp:forward>
        <%
            } else {
        %>
                <jsp:forward page="login-error.jsp"/>
        <%
            }
        %>
    </body>
</html>
