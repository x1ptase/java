<%@page import="models.tbAccountDTO"%>
<%@page import="models.tbBikeDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bike List Page</title>
    </head>
    <body>
        <%
            tbAccountDTO account=(tbAccountDTO) session.getAttribute("account");
            if(account == null){
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        %>
        <h2>Welcome, <%= account.getFullName()%>!</h2>
        
        <a href="addBike.jsp">Add</a>
        <br/><br/>
        
        <h3>Search Bike</h3>
        <form action="SearchBikeController" method="POST">
            <input type="text" name="keyword" value="${requestScope.keyword != null ? requestScope.keyword : ''}" placeholder="Enter bike name..." />
            <input type="submit" value="Search" />
        </form>
        
        <%
            List<tbBikeDTO> bikeList=(List<tbBikeDTO>) request.getAttribute("bikeList");
            String keyword=(String) request.getAttribute("keyword");
            
            if(bikeList != null && !bikeList.isEmpty()){
        %>
        <h3>Search Results</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Model</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int no=1;
                    for(tbBikeDTO bike : bikeList){
                %>
                <tr>
                    <td><%= no++ %></td>
                    <td><%= bike.getId() %></td>
                    <td><%= bike.getName() %></td>
                    <td><%= bike.getQuantity() %></td>
                    <td><%= bike.getModel() %></td>
                    <td>
                        <form action="RemoveBikeController" method="POST" style="display:inline;">
                            <input type="hidden" name="bikeID" value="<%= bike.getId() %>" />
                            <input type="hidden" name="keyword" value="<%= keyword != null ? keyword : "" %>" />
                            <input type="submit" value="Remove" onclick="return confirm('Are you sure you want to remove this bike?');" />
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else if(keyword != null && !keyword.trim().isEmpty()){
        %>
        <p>No bikes found matching "<%= keyword %>"</p>
        <%
            }
        %>
        
        <br/>
        <form action="LogoutController">
            <input type="submit" value="Logout" />
        </form>
    </body>
</html>
