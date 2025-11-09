<%@page import="models.tbAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Bike Page</title>
    </head>
    <body>
        <%
            tbAccountDTO account=(tbAccountDTO) session.getAttribute("account");
            if(account == null){
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        %>
        <h2>Add New Bike</h2>
        
        <form action="AddBikeController" method="POST">
            <table>
                <tr>
                    <td>Bike ID:</td>
                    <td><input type="text" name="txtBikeID" value="${param.txtBikeID}" required /></td>
                </tr>
                <tr>
                    <td>Bike Name:</td>
                    <td><input type="text" name="txtBikeName" value="${param.txtBikeName}" required /></td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><input type="number" name="txtQuantity" value="${param.txtQuantity}" min="0" required /></td>
                </tr>
                <tr>
                    <td>Model:</td>
                    <td><input type="text" name="txtModel" value="${param.txtModel}" required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Add" />
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
            </table>
        </form>
        
        <p style="color: red">${requestScope.error}</p>
        <p style="color: green">${requestScope.success}</p>
        
        <%
            String success = (String) request.getAttribute("success");
            if(success != null && !success.isEmpty()){
        %>
        <br/>
        <a href="bikeList.jsp">Back</a>
        <%
            }
        %>
        
        <br/><br/>
        <a href="bikeList.jsp">Back to Search</a>
    </body>
</html>

