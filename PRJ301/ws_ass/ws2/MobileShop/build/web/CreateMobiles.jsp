<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Mobile</title>
    </head>
    <body>
        <c:if test="${sessionScope.user == null || sessionScope.user.role != 1}">
            <h2>Access Denied</h2>
            <p style="color: red">Only admin can create mobiles.</p>
            <a href="ViewController">Back to List</a>
        </c:if>
        
        <c:if test="${sessionScope.user != null && sessionScope.user.role == 1}">
            <h2>Create New Mobile</h2>
            
            <form action="CreateMobilesController" method="POST">
                Mobile ID * <input type="text" name="mobileId" required /><br/>
                Mobile Name * <input type="text" name="mobileName" required /><br/>
                Description <textarea name="description"></textarea><br/>
                Price * <input type="number" name="price" step="0.01" min="0" required /><br/>
                Year of Production * <input type="number" name="yearOfProduction" min="1900" max="2100" required /><br/>
                Quantity * <input type="number" name="quantity" min="0" required /><br/>
                Not Sale <input type="checkbox" name="notSale" value="on" /><br/>
            
            <p style="color: red">${requestScope.msg}</p>
            
                <input type="submit" value="Create Mobile" />
                <a href="ViewController">Cancel</a>
            </form>
        </c:if>
    </body>
</html>
