<%-- 
    Document   : C_Choose
    Created on : Oct 15, 2025, 3:46:19 PM
    Author     : x1pta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Decision Demo</title>
    </head>
    <body>
        <h1>Decision Demo</h1>
        <form>
            Number: <input type="text" name="number" value="${param.number}" /><br/>
            <input type="submit" value="submit" />
            <br/>
            <c:catch var="ex">
                <c:choose>
                    <c:when test="${empty param.number}">
                    </c:when>
                    <c:when test="${param.number % 2 != 0}">
                        ${param.number} is an odd number.
                    </c:when>
                    <c:when test="${param.number % 2 == 0}">
                        ${param.number} is an even number.
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:catch>
            <c:if test="${ex != null}">
                The exception is: ${ex}<br/>
                The exception message:<c:out value="${ex.message}"/><br/>
            </c:if>  
        </form>
    </body>
</html>
