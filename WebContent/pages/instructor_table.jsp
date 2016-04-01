<%@ page import="model.Question" 
%>
<%@ page import="java.util.List" %>
<jsp:useBean id="instructor" scope="session" class="bean.instructor.InstructorBean"/>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1. %>
<% if(session != null && ((session.getAttribute("userid") != null ) || (session.getAttribute("userid") != ""))
        && ((session.getAttribute("role") != null) && session.getAttribute("role") != "")) {
    if (session.getAttribute("role").equals("admin")) { %>
        <!DOCTYPE html>
        <html lang="en">
<%
    // check if we need to remove, else display the page
    if (request.getParameter("remove") != null && request.getParameter("remove").equals("true")){
        System.out.println("attempting to remove id: " + request.getParameter("id"));
        instructor.removeQuestionById(Integer.parseInt(request.getParameter("id")));

        // finally redirect to save the question
        response.sendRedirect("instructor_table.jsp");
    }
%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Instructor's Questions</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="../dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->

    <%--Navbar--%>
    <jsp:include page="navbar.jsp" />

    <jsp:setProperty name="instructor" property="*" />
    <%! int counter; %>
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">List of Quiz Questions</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Quiz Description</th>
                            <th>Type</th>
                            <th>Difficulty</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            counter = 0;
                        	List<Question> questions = instructor.getAllQuestions();
                            for(int i = 0; i < questions.size(); i++){
                            	Question question = questions.get(i);
                                if(!question.isUsedInQuiz()){
                                    counter++;
                        %>
                        <tr>
                            <td><%=counter%></td>
                            <td><%=question.getText()%></td>
                            <td><%=question.getType()%></td>
                            <td><%=question.getDifficulty()%></td>
                            <td>
                                <a href="question_create.jsp?id=<%=question.getId()%>&edit=true">Edit</a> |
                                <a href="instructor_table.jsp?id=<%=question.getId()%>&remove=true">Delete</a>
                            </td>
                        </tr>
                        <% } } %>
                    </tbody>
                </table>
                <%--<jsp:getProperty name="quiz" property="currentQuestion"/>--%>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->


    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="../bower_components/raphael/raphael-min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
<% } else { response.sendRedirect("index.jsp");}
}else {response.sendRedirect("login.jsp");} %>
