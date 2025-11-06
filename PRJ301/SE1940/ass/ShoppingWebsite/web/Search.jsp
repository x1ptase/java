<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>User Shopping</h1>
        <p>
            <a href="<c:url value='/ViewPizzaListController'/>">Go to Pizza Menu</a>
            |
            <a href="<c:url value='/LogoutController'/>">Logout</a>
        </p>
    </body>
</html>
