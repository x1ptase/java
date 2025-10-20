<%-- 
    Document   : index
    Created on : Oct 21, 2025, 12:14:49 AM
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
        <h1>Calculate + - * /</h1>
        <form>
            Num 1 <input type="text" name="num1" value="" /><br/>
            Num 2 <input type="text" name="num2" value="" /><br/>
            Operation <select name="operation">
                <option value="+">Plus</option>
                <option value="-">Abtr</option>
                <option value="*">Multi</option>
                <option value="/">Div</option>
            </select>
            <input type="submit" value="Calculate" />
        </form>
        <%
            double res=0;
            String error="";
        %>
   
        <%
         String num1=request.getParameter("num1");
         String num2=request.getParameter("num2");
         String operation=request.getParameter("operation");
         try{
            double n1=Double.parseDouble(num1);
            double n2=Double.parseDouble(num2);
            
            switch(operation){
                case"+":
                    res=n1+n2;
                    break;
                case"-":
                    res=n1-n2;
                    break;
                case"*":
                    res=n1*n2;
                    break;
                case"/":
                    if(n2 != 0){
                        res=n1/n2;
                    } else{
                        error="Enter n2 != 0";
                    }
                    break;
            }
         } catch(Exception ex){
         }
        %>
        
        <% if(error.equals("") == false){ %>
            <%= error %>
        <% } %>
        
        <% if(res != 0){ %>
            <%= res %>
        <% } %>
    </body>
</html>
