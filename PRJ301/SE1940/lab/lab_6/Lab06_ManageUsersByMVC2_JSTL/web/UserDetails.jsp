<%-- 
    Document   : UserDetails
    Created on : Oct 18, 2025, 4:29:57 PM
    Author     : x1pta
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.DTO.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UserDetails</title>
    </head>
    <body>
        <form method="post">
            <input type="submit" formaction="LogoutController" value="Logout"/><br/>
        </form>
        <c:if test="${userLoggedIn != null}">
            <c:set var="lastName" value="${userLoggedIn.lastName}"/>
        </c:if>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:set var="user" value="${requestScope.userDetails}"/>
        <c:if test="${userDetails != null}">
            <h1>User Details</h1>
            <c:set var="message" value="${requestScope.message}"/>
            <c:if test="${message != null}">
                ${message}
            </c:if>
            <form action="UserController" method="post">
            <c:set var="error" value="${requestScope.ErrorDetails}"/>
            UserName <input type="text" name="txtUserName" value="${user.userName}" readonly="true" />
            <c:if test="${not empty error.userNameError}">
                <text style="color: red">${error.userNameError}</text>
            </c:if><br/>
            Password <input type="password" name="txtPassword" value="${user.password}" />
            <c:if test="${not empty error.passwordError}">
                <text style="color: red">${error.passwordError}</text>
            </c:if><br/>
            LastName <input type="text" name="txtLastName" value="${user.lastName}" />
            <c:if test="${not empty error.lastNameError}">
                <text style="color: red">${error.lastNameError}</text>
            </c:if><br/>

            <input type="hidden" value="${searchValue}" name="txtSearchValue"/>

            <input type="checkbox" name="chkIsAdmin" 
                <c:if test="${user.isAdmin == true}">checked</c:if>
                <c:if test="${userLoggedIn.isAdmin == false}">disabled</c:if>
            />isAdmin<br/>
            <input type="submit" value="Update" name="action" /><br/>
        </form>
        <c:if test="${userLoggedIn.isAdmin}">
            <a href="UserController?action=Search&txtSearchValue=${searchValue}">Back</a><br/>
        </c:if>
        <c:if test="${!userLoggedIn.isAdmin}">
            <a href="Login.jsp">Back</a><br/>
        </c:if>
    </c:if>
    </body>
</html>
