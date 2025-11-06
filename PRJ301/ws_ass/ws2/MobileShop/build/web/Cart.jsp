<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Giỏ hàng của bạn</title>
    <style>
        body { font-family: Arial; background: #f7f7f7; }
        .cart-container { width: 850px; background: #fff; margin: 40px auto; border-radius: 10px; box-shadow: 0 0 12px #ccc; padding: 32px 24px; }
        h2 { text-align: center; color: #333; margin-bottom: 28px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 9px 12px; text-align: center; font-size: 16px; }
        th { background: #4285f4; color: #fff; }
        tr:nth-child(even) { background: #f8fbff; }
        .btn { background: #e53935; color: #fff; border: none; border-radius: 4px; padding: 6px 14px; cursor: pointer; font-weight: bold; }
        .btn:hover { background: #b71c1c; }
        .btn-blue { background: #42a5f5; }
        .btn-blue:hover { background: #1565c0; }
        .navi-link { display: inline-block; margin: 11px 4px 0px 0; padding: 7px 18px; background: #43a047; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; }
        .navi-link:hover { background: #2b8e2b; }
        .empty { color: #777; text-align: center; font-size: 18px; padding: 36px 0; }
        .bigtotal { font-size: 21px; color: #0288d1; font-weight: bold; }
    </style>
</head>
<body>
<div class="cart-container">
    <h2>🛒 Giỏ hàng của bạn</h2>
    <c:set var="cart" value="${sessionScope.cart}" />
    <c:if test="${cart == null || empty cart.items}">
        <div class="empty">Giỏ hàng của bạn hiện đang rỗng.</div>
        <a href="user" class="navi-link">&larr; Tiếp tục mua hàng</a>
    </c:if>
    <c:if test="${cart != null && not empty cart.items}">
        <table>
            <tr>
                <th>Mã sản phẩm</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
                <th>Thao tác</th>
            </tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.mobile.mobileId}</td>
                    <td>${item.mobile.mobileName}</td>
                    <td>${item.mobile.price}</td>
                    <td>
                        <form method="post" action="cart" style="display:inline;">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="mobileId" value="${item.mobile.mobileId}" />
                            <input type="number" name="qty" value="${item.quantity}" min="1" style="width:60px; font-size:15px; padding:3px 5px; text-align:center;"/>
                            <button class="btn btn-blue" type="submit">Cập nhật</button>
                        </form>
                    </td>
                    <td>${item.total}</td>
                    <td>
                        <form method="post" action="cart" style="display:inline;">
                            <input type="hidden" name="action" value="remove"/>
                            <input type="hidden" name="mobileId" value="${item.mobile.mobileId}" />
                            <button class="btn" type="submit" onclick="return confirm('Xóa sản phẩm này khỏi giỏ?');">Xóa</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4" align="right" class="bigtotal">Tổng tiền:</td>
                <td colspan="2" class="bigtotal">${cart.total}</td>
            </tr>
        </table>
        <a href="user" class="navi-link">&larr; Tiếp tục mua hàng</a>
        <form action="checkout" method="post" style="display:inline;">
            <button type="submit" class="navi-link" style="background:#1976d2;padding:8px 25px;">Thanh toán</button>
        </form>
    </c:if>
</div>
</body>
</html>