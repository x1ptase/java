<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
    <style>
        body { font-family: Arial; background:#f7f7f7; }
        .wrap { width: 1000px; margin: 30px auto; background:#fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px #ccc; }
        h2 { margin-top: 0; }
        table { width: 100%; border-collapse: collapse; margin: 10px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background:#4285f4; color:#fff; }
        .actions form { display: inline-block; margin: 0 2px; }
        .btn { padding: 6px 12px; border: 0; border-radius: 4px; cursor: pointer; color:#fff; }
        .btn-del { background:#e53935; }
        .btn-upd { background:#1e88e5; }
        .btn-add { background:#43a047; margin-top: 8px; }
        .search { margin: 10px 0; }
        input[type=text], input[type=number] { padding: 5px; }
    </style>
</head>
<body>
<div class="wrap">
    <h2>Quản lý sản phẩm</h2>

    <form class="search" method="get" action="staff">
        <input type="text" name="keyword" placeholder="Tìm theo tên hoặc mã">
        <button type="submit">Tìm kiếm</button>
    </form>

    <table>
        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Năm SX</th>
            <th>Số lượng</th>
            <th>Đang bán?</th>
            <th>Mô tả</th>
            <th>Thao tác</th>
        </tr>
        <c:forEach var="m" items="${mobiles}">
            <tr>
                <td>${m.mobileId}</td>
                <td>${m.mobileName}</td>
                <td>${m.price}</td>
                <td>${m.yearOfProduction}</td>
                <td>${m.quantity}</td>
                <td><c:out value="${m.notSale ? 'Không' : 'Có'}"/></td>
                <td>${m.description}</td>
                <td class="actions">
                    <form method="post" action="staff">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="mobileId" value="${m.mobileId}"/>
                        <button class="btn btn-del" onclick="return confirm('Xóa sản phẩm này?');">Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty mobiles}">
            <tr><td colspan="8" style="color:#777;font-style:italic;">Không tìm thấy sản phẩm nào!</td></tr>
        </c:if>
    </table>

    <h3>Thêm / Cập nhật sản phẩm</h3>
    <form method="post" action="staff">
        <input type="hidden" name="action" value="create"/>
        <input type="text" name="mobileId" placeholder="Mã" required>
        <input type="text" name="mobileName" placeholder="Tên" required>
        <input type="number" name="price" placeholder="Giá" step="0.01" required>
        <input type="number" name="yearOfProduction" placeholder="Năm SX">
        <input type="number" name="quantity" placeholder="Số lượng" required>
        <label><input type="checkbox" name="notSale"> Không bán</label>
        <br>
        <input type="text" name="description" placeholder="Mô tả" style="width: 60%;">
        <br>
        <button class="btn btn-add" type="submit">Thêm mới</button>
    </form>

    <p><a href="user">&larr; Về trang người dùng</a> | <a href="cart">Xem giỏ hàng</a></p>
</div>
</body>
</html>
