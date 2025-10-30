<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <style>
        body { font-family: Arial; background:#f7f7f7; }
        .wrap { width: 1000px; margin: 30px auto; background:#fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px #ccc; }
        h2 { margin-top: 0; }
        table { width: 100%; border-collapse: collapse; margin: 10px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background:#4285f4; color:#fff; }
        .filter { margin: 10px 0; }
        .add-form { display:inline-block; }
        .btn { padding: 6px 12px; border: 0; border-radius: 4px; cursor: pointer; color:#fff; background:#1e88e5; }
    </style>
</head>
<body>
<div class="wrap">
    <h2>Danh sách sản phẩm</h2>

    <form class="filter" method="get" action="user">
        Giá từ: <input type="number" step="0.01" name="min" style="width:120px;">
        đến: <input type="number" step="0.01" name="max" style="width:120px;">
        <button type="submit">Lọc</button>
        <a href="user">Bỏ lọc</a>
    </form>

    <table>
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Năm SX</th>
            <th>Còn bán</th>
            <th>Thêm vào giỏ</th>
        </tr>
        <c:forEach var="m" items="${mobiles}">
            <tr>
                <td>${m.mobileId}</td>
                <td>${m.mobileName}</td>
                <td>${m.price}</td>
                <td>${m.yearOfProduction}</td>
                <td><c:out value="${m.notSale ? 'Không' : 'Có'}"/></td>
                <td>
                    <form class="add-form" method="post" action="cart">
                        <input type="hidden" name="action" value="add"/>
                        <input type="hidden" name="mobileId" value="${m.mobileId}"/>
                        <input type="number" name="qty" value="1" min="1" style="width:60px;">
                        <button class="btn" type="submit">Thêm</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="cart">Xem giỏ hàng</a> | <a href="staff">Trang quản trị</a></p>
</div>
</body>
</html>
