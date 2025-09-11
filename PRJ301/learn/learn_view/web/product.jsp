<%
    String id=request.getParameter("id");
%>
<h3>This is a product <%= id %>!</h3>

<% int x=10; %>
<% if(x > 5) { %>
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
  
<p>
  <c:choose>
    <c:when test="${day == 0}">Sunday</c:when>
    <c:when test="${day == 1}">Monday</c:when>
    <c:otherwise>Saturday</c:otherwise>
  </c:choose>
</p>

<%
  String[] colors={"red", "green", "blue"};
%>
<ul>
  <% for(int i=0; i < colors.length; i++){ %>
    <li><%= colors[i] %></li>
  <% } %>
</ul>

<ul>
  <c:forEach var="color" items="${colors}">
    <li>${color}</li>
  </c:forEach>
</ul>
  
<%
  String[] colors1 = {"red", "green", "blue"};
  int i=0;
%>

<ul>
  <% while(i < colors.length){ %>
    <li><%= colors[i] %></li>
    <% i++; %>
  <% } %>
</ul>