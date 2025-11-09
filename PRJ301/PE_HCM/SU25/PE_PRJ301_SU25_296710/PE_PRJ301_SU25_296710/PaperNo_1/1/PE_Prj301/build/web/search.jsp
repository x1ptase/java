<%-- 
    Document   : search.jsp
    Created on : Nov 9, 2025, 11:03:10 PM
    Author     : x1pta
--%>

<%@page import="pe.model.AccountDto"%>
<%@page import="pe.model.RoomForRentDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <%
            AccountDto account = (AccountDto) session.getAttribute("account");
            if (account == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        %>
        
        <h2>Welcome <%= account.getFullName()%>!</h2>
        
        <a href="SearchController">Search</a> | <a href="LogoutController">Logout</a>
        
        <hr/>
        
        <h3>Search by District Name or Street Name</h3>
        <form action="SearchController" method="GET">
            <input type="text" name="txtSearch" value="${requestScope.searchCriteria != null ? requestScope.searchCriteria : ''}" placeholder="Enter district or street name" />
            <input type="submit" value="Search" />
        </form>
        
        <br/>
        
        <%
            List<RoomForRentDto> searchResults = (List<RoomForRentDto>) request.getAttribute("searchResults");
            String searchCriteria = (String) request.getAttribute("searchCriteria");
            
            if (searchResults != null) {
                if (searchResults.isEmpty()) {
                    if (searchCriteria != null && !searchCriteria.trim().isEmpty()) {
                        out.println("<p>No data matching the search criteria found!</p>");
                    }
                } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Location</th>
                    <th>Description</th>
                    <th>Posted Date</th>
                    <th>Price</th>
                    <th>Tool</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (RoomForRentDto room : searchResults) {
                %>
                <tr>
                    <td><%= room.getId()%></td>
                    <td><%= room.getTitle()%></td>
                    <td><%= room.getLocation()%></td>
                    <td><%= room.getDescription()%></td>
                    <td><%= room.getPostedDate()%></td>
                    <td><%= room.getPrice()%></td>
                    <td>
                        <%
                            // Delete button only enabled/visible when status = 1 (not yet rented)
                            if (room.getStatus() == 1) {
                        %>
                        <form action="DeleteController" method="GET" style="display:inline;">
                            <input type="hidden" name="roomId" value="<%= room.getId()%>" />
                            <input type="hidden" name="searchCriteria" value="<%= searchCriteria != null ? searchCriteria : ""%>" />
                            <input type="submit" value="Delete" />
                        </form>
                        <%
                            }
                            // If status != 1, button is hidden (room is already rented)
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
            }
        %>
    </body>
</html>
