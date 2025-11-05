<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/styleindex.css">
        <style>
            .hint { margin: 20px 0; color: #555; }
        </style>
    </head>
    <body>
        <h1>User Shopping</h1>
        <p class="hint">Trang mua hàng đã được chuyển qua giao diện mới.</p>
        <p>
            <a href="${pageContext.request.contextPath}/ViewPizzaListController">Go to Pizza Menu</a>
            |
            <a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
        </p>
    </body>
</html>
