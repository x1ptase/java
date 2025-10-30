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
                    <form method="post" action="staff" style="display:inline-block;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="mobileId" value="${m.mobileId}"/>
                        <button class="btn btn-del" onclick="return confirm('Xóa sản phẩm này?');">Xóa</button>
                    </form>
                    <form method="get" action="staff" style="display:inline-block;">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="mobileId" value="${m.mobileId}"/>
                        <button class="btn btn-upd" type="submit">Sửa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty mobiles}">
            <tr><td colspan="8" style="color:#777;font-style:italic;">Không tìm thấy sản phẩm nào!</td></tr>
        </c:if>
    </table>

    <h3>Thêm / Cập nhật sản phẩm</h3>
    <c:choose>
        <c:when test="${not empty editMobile}">
            <!-- FORM SỬA ĐẸP -->
            <form method="post" action="staff" class="product-form">
                <input type="hidden" name="action" value="update"/>
                <div class="row">
                    <label>Mã:</label>
                    <input type="text" name="mobileId" value="${editMobile.mobileId}" readonly style="background:#f5f5f5;" />
                    <label>Tên:</label>
                    <input type="text" name="mobileName" value="${editMobile.mobileName}" required />
                    <label>Giá:</label>
                    <input type="number" name="price" value="${editMobile.price}" step="0.01" required />
                </div>
                <div class="row">
                    <label>Năm SX:</label>
                    <input type="number" name="yearOfProduction" value="${editMobile.yearOfProduction}" />
                    <label>Số lượng:</label>
                    <input type="number" name="quantity" value="${editMobile.quantity}" required />
                    <label style="margin-left:12px;"><input type="checkbox" name="notSale" <c:if test='${editMobile.notSale}'>checked</c:if> /> Không bán</label>
                </div>
                <div class="row">
                    <label>Mô tả:</label>
                    <input type="text" name="description" value="${editMobile.description}" style="width: 60%;" />
                </div>
                <div class="row">
                    <button class="btn btn-upd" type="submit">Lưu</button>
                    <a href="staff" class="btn btn-add" style="background:#bbb;color:#fff;text-decoration:none;">Bỏ sửa</a>
                </div>
            </form>
        </c:when>
        <c:otherwise>
            <!-- FORM THÊM ĐẸP -->
            <form method="post" action="staff" class="product-form">
                <input type="hidden" name="action" value="create"/>
                <div class="row">
                    <label>Mã:</label>
                    <input type="text" name="mobileId" placeholder="Mã" required>
                    <label>Tên:</label>
                    <input type="text" name="mobileName" placeholder="Tên" required>
                    <label>Giá:</label>
                    <input type="number" name="price" placeholder="Giá" step="0.01" required>
                </div>
                <div class="row">
                    <label>Năm SX:</label>
                    <input type="number" name="yearOfProduction" placeholder="Năm SX">
                    <label>Số lượng:</label>
                    <input type="number" name="quantity" placeholder="Số lượng" required>
                    <label style="margin-left:12px;"><input type="checkbox" name="notSale"> Không bán</label>
                </div>
                <div class="row">
                    <label>Mô tả:</label>
                    <input type="text" name="description" placeholder="Mô tả" style="width: 60%;">
                </div>
                <div class="row">
                    <button class="btn btn-add" type="submit">Thêm mới</button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
    <style>
    .product-form .row { display: flex; align-items: center; margin-bottom: 8px; }
    .product-form label { min-width: 70px; margin-right: 6px; }
    .product-form input[type=text], 
    .product-form input[type=number] { margin-right: 12px; padding: 5px; }
    .product-form button { margin-left: 0; }
    </style>

    <p><a href="user">&larr; Về trang người dùng</a> | <a href="cart">Xem giỏ hàng</a></p>
</div>
</body>
</html>
