<HTML>
<jsp:useBean id="user" class="bean.user.UserBeanBase" scope="session"/>

<%  String email = request.getParameter("email");
    String salt = request.getParameter("password");

    // Empty field check
    if (email == null || salt == null || email.isEmpty() || salt.isEmpty()) {
        session.setAttribute("error", "Empty fields");
        response.sendRedirect("register.jsp");
    }
    else {
        session.setAttribute("error", null);

        // Check if user name is in database
        if (!user.isRegistered(request.getParameter("email"))) {
            System.out.println(user.toString());
            user.setEmail(email);
            user.setPassword(salt);
            user.setName(request.getParameter("name"));
            user.setRole(request.getParameter("role"));

            // Save user to DB
            if (user.commitUser()) {
                session.setAttribute("userid", user.getEmail());
                session.setAttribute("role", request.getParameter("role"));

                // Redirect according to "role"
                if (user.getRole(email).equals("admin")) {
                    response.sendRedirect("question_create.jsp");
                } else response.sendRedirect("take_quiz.html");
            } else response.sendRedirect("register.jsp");
        } else {
            session.setAttribute("error", "Username Exists");
            response.sendRedirect("register.jsp");
        }
    }

//    } else {
//        if (user.userLogIn(email, salt) == true){
//            session.setAttribute("userid", email);
//            session.setAttribute("role", user.getRole());
//        } else response.sendRedirect("register.jsp");
//    }
//
//    if ((session.getAttribute("userid") != null) || (session.getAttribute("userid") != "")){
//        if (user.getRole().equals("admin")) {
//            response.sendRedirect("question_create.jsp");
//        } else response.sendRedirect("take_quiz.html");
//    }
%>
</HTML>
