<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page import="java.util.List"%>
<%@page import="pe.model.UniversityDto"%>
<%@page import="pe.model.UserDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <!--your code here-->
        <%
            UserDto user = (UserDto) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        %>
        <h2>Welcome <%= user.getFullName()%>!</h2>

        <hr/>

        <h3>Search by University Name</h3>
        <p>Enter name, short name, or part of a name</p>
        <form action="MainController?action=Search" method="GET">
            <input type="text" name="txtSearch" value="${requestScope.searchCriteria != null ? requestScope.searchCriteria : ''}" />
            <input type="submit" value="Search" />
        </form>

        <br/>

        <%
            List<UniversityDto> searchResults=(List<UniversityDto>) request.getAttribute("searchResults");
            String searchCriteria=(String) request.getAttribute("searchCriteria");

            if(searchResults != null) {
                if(searchResults.isEmpty()) {
                    if(searchCriteria != null && !searchCriteria.trim().isEmpty()) {
                        out.println("<p style=\"color:red\">No data matching the search criteria found!</p>");
                    }
                } else{
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>University</th>
                    <th>Short name</th>
                    <th>City</th>
                    <th>Region</th>
                    <th>Type</th>
                    <th>Founded year</th>
                    <th>Students</th>
                    <th>Faculties</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for(UniversityDto uni : searchResults) {
                %>
                <tr>
                    <td><%= uni.getId()%></td>
                    <td><%= uni.getName()%></td>
                    <td><%= uni.getShortName()%></td>
                    <td><%= uni.getCity()%></td>
                    <td><%= uni.getRegion()%></td>
                    <td><%= uni.getType()%></td>
                    <td><%= uni.getFoundedYear()%></td>
                    <td><%= uni.getTotalStudents()%></td>
                    <td><%= uni.getTotalFaculties()%></td>
                    <td>
                        <%
                            }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
                }
        %>
    </body>
</html>
