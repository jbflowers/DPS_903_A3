<HTML>
<jsp:useBean id="user" class="bean.user.UserBeanBase" scope="session"/>

<%  String email = request.getParameter("email");
    String salt = request.getParameter("password");

    // Empty field check
    if (email == null || salt == null || email.isEmpty() || salt.isEmpty()) {
        session.setAttribute("loginerror", "Empty fields");
        response.sendRedirect("login.jsp");
    }
    else {
        // Check user credentials
        if (user.userLogIn(email, salt)) {
            session.setAttribute("userid", email);
            session.setAttribute("role", user.getRole(email));
            session.setAttribute("loginerror", null);
            response.sendRedirect("index.jsp");
        }  else {
            session.setAttribute("loginerror", "Invalid credentials");
            response.sendRedirect("login.jsp");
        }
    }
%>
</HTML>

