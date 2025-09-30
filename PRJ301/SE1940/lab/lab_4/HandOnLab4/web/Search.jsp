<%-- 
    Document   : Search
    Created on : Sep 30, 2025, 9:25:13 PM
    Author     : x1pta
--%>
<%@page import="Models.DTO.User" %>
<%@page import="Models.DAO.UserDAO" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Users</title>
    </head>
    <body>
        <%
            String lastName = null, searchValue = null;
            User userLoggedIn = (User) session.getAttribute("userLoggedIn");
            if (userLoggedIn != null) {
                lastName = userLoggedIn.getLastName();
            }
            searchValue = request.getParameter("txtSearchValue");
        %>    
        <h3>Welcome to <text style="color: red"><%=lastName%></text></h3>
        <h1>Search user by last name</h1>
        <form action="UserController" method="POST">
            Enter search value
            <input type="text" name="txtSearchValue" value="<%=searchValue != null ? searchValue : ""%>" /><br/>
            <input type="submit" value="Search" name="action" />
        </form>
    </body>
    <%
        UserDAO userDAO = new UserDAO();
        List<User> userList = (List<User>) request.getAttribute("SearchResult");
        if (userList != null) {
    %>
                <table border="1">
                    <thread>
                        <tr>
                            <th>No.</th>
                            <th>UserName</th>
                            <th>Password</th>
                            <th>LastName</th>
                            <th>Role</th>
                            <th colspan="2">Action</th>
                        </tr>
                    </thread>
                        <tbody>
                        <%
                            int count = 0;
                            for(User user : userList){
                        %>
                        
                            <tr>
                                <td><%=(++count)%></td>
                                <td><%=user.getUserName()%></td>
                                <td><%=user.getPassword()%></td>
                                <td><%=user.getLastName()%></td>
                                <td><input type="checkbox" <%if (user.isIsAdmin()) { %> checked <% }%> /></td>
                                <td><a href="UserController?action=Delete&UserName=<%=user.getUserName()%>&txtSearchValue=<%=searchValue%>">Delete</a></td>
                                <td><a href="UserController?action=Update&UserName=<%=user.getUserName()%>&txtSearchValue=<%=searchValue%>">Update</a></td>
                            </tr>
                        <%
                            } // end for
                        %>
                        </tbody>
                </table>
                <h3>Number of users found: <%=userList.size()%></h3>
    <%
        } // end if
        else if (searchValue != null) {
    %>
                <h3>No users were found.</h3>
    <%
        }
    %>
    <a href="<%=request.getContextPath()%>/Login.html">Back to login</a><br/>
</html>
