<%-- 
    Document   : Search
    Created on : Oct 29, 2025, 1:20:45 PM
    Author     : x1pta
--%>

<%@page import="Models.DTO.User" %>
<%@page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <c:set var="userLoggedIn" value="${userLoggedIn}" />
        <c:if test="${userLoggedIn != null}">
            <c:set var="lastName" value="${userLoggedIn.lastName}"/>
        </c:if>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <h3>Welcome to <span style="color: red">${lastName}</span></h3>
        <form method="POST">
            <input type="submit" formaction="logout" value="Logout" />
        </form>
        <h1>Search user by last name</h1>
        <form action="UserController" method="post">
        Enter search value
        <input type="text" name="txtSearchValue"
               value="${searchValue!=null?searchValue:''}"/>
        <input type="submit" value="Search" name="action" />
    </form>

    <c:set var="userList" value="${requestScope.SearchResult}"/>
    <c:set var="count" value="1"/>

    <c:if test="${userList != null}">
        <table border='1'>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>LastName</th>
                    <th>Role</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${count}</td>
                        <td>${user.userName}</td>
                        <td>${user.password}</td>
                        <td>${user.lastName}</td>
                        <td><input type="checkbox" ${user.isAdmin ? 'checked' : ''} /></td>
                        <td><a href="UserController?action=Delete&userName=${user.userName}&txtSearchValue=${searchValue}">
                            Delete
                        </a></td>
                        <td><a href="UserController?action=Details&userName=${user.userName}&txtSearchValue=${searchValue}">
                            View
                        </a></td>
                    </tr>
                    <c:set var="count" value="${count+1}"/>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:set var="message" value="${requestScope.message}"/>
    <c:if test="${message != null}">
        ${message}
    </c:if>

    <h3>Number of users: ${userList != null ? fn:length(userList) : 0}</h3>
    <c:if test="${searchValue!=null}">
        <c:if test="${userList == null || fn:length(userList) == 0}">
            <h3>No users were found.</h3>
        </c:if>
    </c:if>
    </body>
</html>
