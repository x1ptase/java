<%
    String id=request.getParameter("id");
%>
<h3>This is a product <%= id %>!</h3>

<% int x=10; %>
<% if (x > 5) { %>
  <p>x is greater than 5</p>
<% } else { %>
  <p>x is not greater than 5</p>
<% } %>

<!-- code in JSTL -->
<c:set var="x" value="10" />

<c:if test="${x > 5}">
  <p>x is greater than 5</p>
</c:if>
<c:if test="${x <= 5}">
  <p>x is not greater than 5</p>
</c:if>
  
