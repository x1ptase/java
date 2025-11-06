<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="LoginController" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>

            <p style="color: red">${requestScope.msg}</p>

            <%
                String status = request.getParameter("status");
                if(status != null && status.equals("logoutSuccess")){
            %>
            <p style="color: green">Logout successfully</p>
            <%
                }
            %>

            <input type="submit" value="Sign in & Continue" />
        </form>  
    </body>
</html>
