<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Đăng nhập</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; }
        .login-container { width: 350px; margin: 90px auto; background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 0 14px #aaa; }
        .login-container h2 { text-align: center; color: #333; margin-bottom: 20px; }
        .form-input { width: 100%; padding: 8px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 5px; }
        .btn { width: 100%; background: #4285f4; color: #fff; font-size: 17px; padding: 10px; border: none; border-radius: 5px; cursor: pointer; }
        .btn:hover { background: #3367d6; }
        .error { color: red; text-align: center; margin-bottom: 10px; }
        .home-link { display: block; margin-top: 20px; text-align: center; text-decoration: none; color: #4285f4; font-size: 15px; }
        .home-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập hệ thống</h2>
        <form method="post" action="login">
            <input class="form-input" name="userId" placeholder="Tài khoản/User ID" autofocus required />
            <input class="form-input" type="password" name="password" placeholder="Mật khẩu/Password" required />
            <button class="btn" type="submit">Đăng nhập</button>
        </form>
        <c:if test="${not empty errorMsg}">
            <div class="error">
                ${msg}
            </div>
        </c:if>
        <a class="home-link" href="user">&larr; Về trang mua hàng</a>
    </div>
</body>
</html>