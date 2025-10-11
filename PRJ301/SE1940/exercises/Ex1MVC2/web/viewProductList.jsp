<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
</head>
<body>
    <a href="MainController?action=createNew">Create New</a>
    
    <table border="1">
        <thead>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty products}">
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.productID}</td>
                            <td>${product.productName}</td>
                            <td>${product.unitPrice}</td>
                            <td>${product.quantity}</td>
                            <td>
                                <a href="MainController?action=viewDetail&productId=${product.productID}">Edit</a>
                                <a href="MainController?action=viewDetail&productId=${product.productID}">Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5">No products found</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>
