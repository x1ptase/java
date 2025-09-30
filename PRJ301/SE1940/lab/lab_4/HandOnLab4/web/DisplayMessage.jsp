<%-- 
    Document   : DisplayMessage
    Created on : Sep 30, 2025, 10:22:24 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information</title>
    </head>
    <body>
        <h1><%=request.getAttribute("action")%> </h1>
        <%
            String message=(String)request.getAttribute("message");
            if(message != null){
        %>
        <h3 style="color: red"><=%message%></h3>
        <%
            }
        %>
        <a href=<%=request.getAttribute("page")%>>Back</a><br/>
    </body>
</html>
