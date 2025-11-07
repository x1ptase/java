<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body style="margin:0; min-height:100vh; display:flex; align-items:center; justify-content:center; background:#f7f7f7;">
        <form action="LoginController" method="POST" style="background:#ffffff; padding:24px 28px; border-radius:8px; box-shadow:0 4px 16px rgba(0,0,0,0.08); min-width:300px;">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input style="margin: 5px 0 0 4px" type="password" name="txtPassword" value="" /><br/>

            <p style="color: red; margin:8px 0 12px 0;">${requestScope.msg}</p>

            <input style="background-color: #16a34a; color: white; border:none; padding:8px 12px; border-radius:6px; cursor:pointer;" type="submit" value="Sign in & Continue" />
        </form>  
    </body>
</html>
