<%@page import="Models.Entities.User"%>
<p>You are successfully logged in!</p>
<%
    User obj=(User)request.getAttribute("UserLoggedIn");
%>
<p style="color: green">Welcome, <%=obj.getName() %></p>
