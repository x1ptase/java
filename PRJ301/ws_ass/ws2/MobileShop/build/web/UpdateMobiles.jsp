<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dao" class="models.MobilesDAO" />
<%
    String mobileId = request.getParameter("mobileId");
    if (mobileId != null) {
        try {
            models.MobilesDTO mobile = dao.findById(mobileId);
            if (mobile != null) {
                request.setAttribute("mobile", mobile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Mobile</title>
    </head>
    <body>
        <c:if test="${sessionScope.user == null || sessionScope.user.role != 1}">
            <h2>Access Denied</h2>
            <p style="color: red">Only admin can update mobiles.</p>
            <a href="ViewController">Back to List</a>
        </c:if>
        
        <c:if test="${sessionScope.user != null && sessionScope.user.role == 1}">
            <h2>Update Mobile</h2>
            
            <c:if test="${mobile != null}">
            <form action="UpdateMobilesController" method="POST">
                <input type="hidden" name="mobileId" value="${mobile.mobileId}" />
                Mobile ID: ${mobile.mobileId}<br/>
                Mobile Name * <input type="text" name="mobileName" value="${mobile.mobileName}" required /><br/>
                Description <textarea name="description">${mobile.description}</textarea><br/>
                Price * <input type="number" name="price" step="0.01" min="0" value="${mobile.price}" required /><br/>
                Year of Production * <input type="number" name="yearOfProduction" min="1900" max="2100" value="${mobile.yearOfProduction}" required /><br/>
                Quantity * <input type="number" name="quantity" min="0" value="${mobile.quantity}" required /><br/>
                Not Sale <input type="checkbox" name="notSale" value="on" ${mobile.notSale ? 'checked' : ''} /><br/>
                
                <p style="color: red">${requestScope.msg}</p>
                
                <input type="submit" value="Update Mobile" />
                <a href="ViewController">Cancel</a>
            </form>
        </c:if>
        
            <c:if test="${mobile == null}">
                <p style="color: red">Mobile not found!</p>
                <a href="ViewController">Back to List</a>
            </c:if>
        </c:if>
    </body>
</html>
