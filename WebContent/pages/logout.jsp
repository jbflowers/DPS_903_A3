<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 2016-03-30
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    if (session != null) {
        session.setAttribute("userid", null);
        session.setAttribute("error", null);
        session.setAttribute("loginerror", null);
        session.setAttribute("role", null);
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
%>
