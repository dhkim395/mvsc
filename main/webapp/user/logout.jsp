<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 25. 6. 11.
  Time: 오후 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  session.invalidate();
  response.sendRedirect("login.jsp");
%>