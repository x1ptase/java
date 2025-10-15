<%-- 
    Document   : decision
    Created on : Oct 15, 2025, 3:27:30 PM
    Author     : x1pta
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Decision</title>
    </head>
    <body>
        <h1>Decision Demo</h1>
        <c:set var="num1" value="20" scope="page"/>
        <c:set var="num2" value="60" scope="page"/>
        <c:if test="${num1 < num2}">
            Num1 is less than num 2
        </c:if>
    </body>
</html>
