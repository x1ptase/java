<%-- 
    Document   : Iterator
    Created on : Oct 15, 2025, 4:36:17 PM
    Author     : x1pta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iterator Demo</title>
    </head>
    <body>
        <h1>Iterator Demo</h1>
        <c:set var="language" value="Core Java: Servlet; JSP: EJB" scope="page"/>
        <% String[] names={"Sun Microsystems", "Microsoft", "Oracle", "IBM"}; %>
        <b>Company</b><br/>
        <c:forEach var="company" items="<%=names%>">
            <c:out value="${company}"/><br/>
        </c:forEach><hr/>
        
        <b>Language</b><br/>
        <c:forTokens var="lang" items="${language}" delims=":;">
            <c:out value="${lang}"/><br/>
        </c:forTokens><hr/>
        
        <b>Counter</b><br/>
        <c:forEach var="counter" begin="1" end="10" step="1" varStatus="status">
            <c:out value="${11-counter}"/>
            ${not status.last ? '<br/>' : '<hr/>'}
        </c:forEach>
    </body>
</html>
