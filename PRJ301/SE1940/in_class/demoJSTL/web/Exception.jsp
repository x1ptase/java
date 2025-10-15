<%-- 
    Document   : Exception
    Created on : Oct 15, 2025, 3:36:14 PM
    Author     : x1pta
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sample Exception</title>
    </head>
    <body>
        <h1>Sample Exception</h1>
        <form>
            Number 1: <input type="text" name="num1" value="${param.num1}"/><br/>
            Number 2: <input type="text" name="num2" value="${param.num2}"/><br/>
            <c:catch var="ex">
                <c:if test="${not empty param.num1 and not empty param.num2}">
                    <c:set var="division" value="${param.num1 / param.num2}"/>
                    Division: <c:out value="${division}"/><br/>
                </c:if><br/>
            </c:catch>
            <input type="submit" value="Divide" /><br/>
            <c:if test="${ex != null}">
                The exception is: ${ex}<br/>
                The exception message:<c:out value="${ex.message}"/><br/>
            </c:if>    
        </form>
    </body>
</html>
