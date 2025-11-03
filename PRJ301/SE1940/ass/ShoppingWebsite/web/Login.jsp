<%-- 
    Document   : Login
    Created on : Nov 2, 2025, 11:25:00 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
        
        <link rel="stylesheet" type="text/css" 
              href="${pageContext.request.contextPath}/resource/css/login.css">
        
    </head>
    <body>
        <div class="login-container">
            
            <form action="LoginController" method="POST">
                
                <div class="input-group">
                    <label for="txtUsername">Username</label>
                    <input type="text" id="txtUsername" name="txtUsername" required/>
                </div>
                
                <div class="input-group">
                    <label for="txtPassword">Password</label>
                    <input type="password" id="txtPassword" name="txtPassword" required/>
                </div>
                
                
                <%-- error login --%>
                <p style="color: red; text-align: center; font-weight: bold">${requestScope.msg}</p>

                <%-- logout success --%>
                <%
                    String status=request.getParameter("status");
                    if(status != null && status.equals("logoutSuccess")){
                %>
                    <p class="message-logout">Logout successfully</p>
                <%
                    }
                %>
                
                
                <input type="submit" class="submit-btn" value="Sign in & continue" />
            </form>
        </div>
    </body>
</html>