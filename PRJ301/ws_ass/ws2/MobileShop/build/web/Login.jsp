<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body> 
        <form action="LoginController" method="POST">
            Username <input type="text" name="txtUserID" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <p style="color: red">${requestScope.msg}</p>
                  
            <input type="submit" value="Sign in & Continue" />
        </form>
    </body>
</html>
