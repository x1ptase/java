<%-- 
    Document   : login-process
    Created on : Oct 15, 2025, 4:13:41 PM
    Author     : x1pta
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.UserBean" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
    <c:set var="url" value="login-error.jsp"/>
    <jsp:useBean id="userBean" class="model.UserBean" scope="session"/>
    <jsp:setProperty name="userBean" property="*"/>
    <c:set var="user" value="${userBean.login()}" scope="session"/>
    <c:if test="${user != null}">
        <c:set var="url" value="login-success.jsp"/>
    </c:if>
    <jsp:forward page="${url}"/>     
    </body>
</html>
