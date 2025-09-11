<%-- 
    Document   : learn_jstl
    Created on : Sep 11, 2025, 11:28:23 PM
    Author     : x1pta
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="x" value="10" />
        <c:if test="${x > 5}">
          <p>x is greater than 5</p>
        </c:if>
          
        <%-- if else nang cao --%>
        <c:set var="day" value="${1}" />
        <p>
          <c:choose>
            <c:when test="${day == 0}">Sunday</c:when>
            <c:when test="${day == 1}"><span style="color: red">Monday</span></c:when>
            <c:when test="${day == 2}">Tuesday</c:when>
            <c:when test="${day == 3}">Wednesday</c:when>
            <c:when test="${day == 4}">Thursday</c:when>
            <c:when test="${day == 5}">Friday</c:when>
            <c:otherwise>Saturday</c:otherwise>
          </c:choose>
        </p>
        
        <%-- catch: bat loi --%>
        <c:catch var="catchException">
        <% int x = 5 / 0; %>
        </c:catch>

        <c:if test="${catchException != null}">
            <p>Error: ${catchException.message}</p>
        </c:if>
            
        <%-- for: duyet mang--%>
        <%
            String[] colors={"red", "blue", "green"};
        %>
        <ul>
            <% for(int i=0; i < colors.length; i++){ %>
                <li><%= colors[i] %></li>
            <% }%>
        </ul>

        <%-- forEach: forEach khong lay gia tri tu Scriplet only Scope--%>
        <c:set var="colors" value="red, blue, green"/> 
        <ul>
            <c:forEach var="color" items="${colors.split(',')}">
            <li>${color}</li>
            </c:forEach>
        </ul>
        
        <%-- forTokens: duyet chuoi tach bang dau phan cach --%>
        <c:forTokens items="apple, banana, orange" delims="," var="fruit">
            <c:out value="${fruit}" /><br />
        </c:forTokens>
    </body>
</html>
