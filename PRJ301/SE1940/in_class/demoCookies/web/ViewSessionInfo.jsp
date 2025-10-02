<%-- 
    Document   : ViewSessionInfo
    Created on : Oct 1, 2025, 4:18:55 PM
    Author     : x1pta
--%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sample Session in JSP</h1>
        <%
            HttpSession sessionInfo=request.getSession(true);
            String message;
            Integer accessCount=(Integer)session.getAttribute("accessCount");
            if(accessCount == null){
                accessCount=0;
                message="Welcome Session Tracking";
            } else{
                message="Comeback";
                accessCount=accessCount + 1;
            }
            SimpleDateFormat formatter=new SimpleDateFormat("E, dd-MM-yyy hh:mm:ss");
        %>
        <h1 style='text-align: center'><%=message%></h1>
        <h2>Information of Session</h2>
        <table border="1" align="center">
            <thead>
                <tr>
                    <th>Infor Type</th>
                    <th>Value</th>
                </tr></thead>
            <tbody>
                <tr>
                    <td>ID</td>
                    <td><%=session.getId()%></td>
                </tr>
                <tr>
                    <td>Creation Time</td>
                    <td><%=formatter.format(session.getCreationTime())%></td>
                </tr>
                <tr>
                    <td>Time of Last Access</td>
                    <td><%=formatter.format(session.getLastAccessedTime())%></td>
                </tr>
                <tr>
                    <td>Number of Previous Accesses</td>
                    <td><%=accessCount%></td>
                </tr>
                <tr>
                    <td>Session Timeout</td>
                    <td><%=session.getMaxInactiveInterval()%></td>
                </tr>
            </tbody>
        </table>
        <% session.setAttribute("accessCount", accessCount);%>
    </body>
</html>
