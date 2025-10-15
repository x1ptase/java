<%-- 
    Document   : welcome
    Created on : Oct 15, 2025, 3:20:23 PM
    Author     : x1pta
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <c:set var="num" value="10"/>
        Display value of num: <c:out value="${num}"/><br/>
        Removing value of num: <c:remove var="num"/><br/>
        Display again value of num: <c:out value="${num}"/><br/>
    </body>
</html>
