<HTML>
<jsp:useBean id="user" class="bean.user.UserBeanBase" scope="session"/>
<%  if (request.getParameter("login") == null) {
        if (user.isRegistered((String)request.getParameter("email")) == false) {

            System.out.println(user.toString());
            user.setEmail(request.getParameter("email"));
            user.setName(request.getParameter("name"));
            user.setPassword(request.getParameter("password"));
            user.setRole(request.getParameter("role"));
            if (user.commitUser() == true) {
                session.setAttribute("userid",user.getEmail());
                session.setAttribute("role", request.getParameter("role"));
            if (request.getParameter("role").equals("admin")){
                response.sendRedirect("question_create.jsp");
            } else response.sendRedirect("take_quiz.html");
        }
    }
    else response.sendRedirect("login.jsp");
%>


</HTML>
