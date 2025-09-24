<%-- 
    Document   : Add
    Created on : Sep 24, 2025, 3:37:07 PM
    Author     : x1pta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            int result, number01, number02;
            String param01, param02;
            public int Add(int number01, int number02){
                return number01 + number02;
            }
        %>
        
        <%
            param01=request.getParameter("txtNumberOne");
            param02=request.getParameter("txtNumberTwo");
            if(param01 != null && param02 != null){
                number01=Integer.parseInt(param01);
                number02=Integer.parseInt(param02);
                result=Add(number01, number02);
            }
        %>
        <h1>Sample JSP</h1>
        <form action="#" method="POST">
            Number One: <input style="margin: 0 0 5px 2px" type="text" name="txtNumberOne" value="<%=number01%>" /><br/>
            Number Two: <input type="text" name="txtNumberTwo" value="<%=number02%>" /><br/>
            <input style="margin: 5px 0 5px 0" type="submit" value="Add" name="btnAdd" /><br/>
            Result: <%=result%>
        </form>
    </body>
</html>
