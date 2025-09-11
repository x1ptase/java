<%-- 
    Document   : learn_jsp
    Created on : Sep 11, 2025, 10:30:42 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2>Content!</h2>
        <!-- Scriptlet: Là nơi bạn có thể viết mã Java bên trong file JSP -->
        <!-- Cach 1: -->
        <%
            int i=1;
            out.println(i + ". Nguyen Van A");
            %> <br>
        <!-- Cach 2: -->
        <jsp:scriptlet>
            i++;
            out.println(i + ". Nguyen Van B");
        </jsp:scriptlet> <br>
        
        <!-- EXPRESSION %= -->
        Today's date: <%= (new java.util.Date()).toLocaleString() %>
        
        <%-- This is JSP comment --%>
        <!-- This is a HTML comment -->
        
        <%-- ACTION --%> 
        <jsp:include page="product.jsp">
            <jsp:param name="id" value="123" /> 
        </jsp:include>
        
        
    </body>
</html>
