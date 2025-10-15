<%-- 
    Document   : FunctionsDemo
    Created on : Oct 15, 2025, 4:52:34 PM
    Author     : x1pta
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Functions JSTL</title>
    </head>
    <body>
        <h1>Functions JSTL Demo</h1>
        fn:length: ${fn:length("This is")}<br/>
        fn:contains: ${fn:contains("abc", "a")}<br/>
        fn:containsIgnoreCase: ${fn:containsIgnoreCase("ABC", "a")}<br/>
        fn:indexOf: ${fn:indexOf("abcd", "c")}<br/>
        fn:split: <br/>
        
        <c:forEach var="item" items="${fn:split('SUN; Oracle; IBM', ';')}">
            ${item}<br/>
        </c:forEach><br/>
        
        fn:substring: ${fn:substring("hello world", 1, 3)}<br/>
        fn:toLowerCase: ${fn:toLowerCase("HELLO")}<br/>
        fn:toUpperCase: ${fn:toUpperCase("hello")}<br/>
        fn:trim: ${fn:trim("    hello world !   ")}<br/>
    </body>
</html>
