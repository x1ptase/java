<%-- 
    Document   : session
    Created on : Sep 24, 2025, 4:04:33 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    if(pageContext.getAttribute("pageCount") == null){
        pageContext.setAttribute("pageCount", new Integer(0));
    }
    if(session.getAttribute("sessionCount") == null){
        session.setAttribute("sessionCount", new Integer(0));
    }
    if(application.getAttribute("appCount") == null){
        application.setAttribute("appCount", new Integer(0));
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Object</title>
    </head>
    <body>
        <h1>Session, Application, PageContext</h1>
        <%
            Integer count=(Integer)pageContext.getAttribute("pageCount");
            pageContext.setAttribute("pageCount", new Integer(count.intValue() + 1));
            Integer count2=(Integer)session.getAttribute("sessionCount");
            session.setAttribute("sessionCount", new Integer(count2.intValue() + 1));
            Integer count3=(Integer)application.getAttribute("appCount");
            application.setAttribute("appCount", new Integer(count3.intValue() + 1));
        %>
        
        <b>Page Count =</b>
        <%= pageContext.getAttribute("pageCount")%><br/>
        
        <b>Session Count =</b>
        <%= session.getAttribute("sessionCount")%><br/>
        
        <b>Application Count =</b>
        <%= application.getAttribute("appCount")%><br/>
        
        <b>Time =</b>
        <%= new java.sql.Time(System.currentTimeMillis())%><br/>
    </body>
</html>
