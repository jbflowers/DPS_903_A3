<%@ page import="model.QuizResponse" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="history" scope="session" class="bean.historybean.HistoryBean"/>

<% if(session != null && ((session.getAttribute("userid") != null ) || (session.getAttribute("userid") != ""))
        && ((session.getAttribute("role") != null) && session.getAttribute("role") != "")) {
    if (!session.getAttribute("role").equals("admin")) {
        history.setUser((String)session.getAttribute("userId"));
    }
%>
<%! List<QuizResponse> quizResponses; %>
<!DOCTYPE html>
<html lang="en">
<%!
    /*
    // temporary variables
    List<Answer> tempAnswers = new ArrayList<Answer>();
    Answer tempAnswer;
    int numOfChoices;
    String[] choices;
    */
%>
<%--<%--%>

    <%--if (request.getParameter("remove") != null && request.getParameter("remove").equals("true")){--%>
        <%--System.out.println("attempting to remove id: " + request.getParameter("id"));--%>
        <%--instructor.removeQuestionById(Integer.parseInt(request.getParameter("id")));--%>

        <%--// finally redirect to save the question--%>
        <%--response.sendRedirect("instructor_table.jsp");--%>
    <%--}--%>
<%--%>--%>

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
                        <th>Mark Received</th>
                        <th>Time took (minutes)</th>
                        <th>User</th>
                        <th>Quiz Id</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        counter = 0;
                        if (!session.getAttribute("role").equals("admin")) {
                            quizResponses = history.getQuizResponsesForUser();
                        } else {
                            quizResponses = history.getAllQuizResponses();
                        }
                            for(QuizResponse quizResponse : quizResponses){
                                counter++;
                    %>
                    <tr>
                        <td><%=counter%></td>
                        <td><%=quizResponse.getMark()%></td>
                        <td><%=(quizResponse.getTimestamp() - quizResponse.getQuiz().getTimestamp())/1000/60%></td>
                        <td><%=quizResponse.getUser().getEmail()%></td>
                        <td><%=quizResponse.getQuiz().getId()%></td>
                    </tr>
                    <% } %>
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

<!-- Custom Form Code -->
<script>
    /*
     $(function() {
     $('#type').change(function(){
     switch( $(this).val()){
     case "mc" || "drop":
     console.log("mc or dropdown");
     break;
     case "number" || "text":
     console.log("num or txt");
     break;
     case "check":
     console.log("checkbox");
     break;
     default:
     console.log("default nada");
     }

     });

     $('#numberOfChoices').on("keyup", function(){
     console.log("key up event here");
     var number = parseInt($(this).val()), i=0, choices = $(".choice").length, copy, button;

     if(choices < number){
     for(i=choices; i<number; i++){
     copy = $($(".choice")[0]).clone(true, true);
     $(copy.find("label")[0]).attr("for", "choice"+(i)).html("Choice #"+(i+1)+(": "));
     $(copy.find("input")[0]).attr("id", "choice"+(i));
     $(copy.find("input")[1]).attr("name", "correct"+(i));
     $(copy.find("input")[2]).attr("name", "correct"+(i));

     button = $($("button")[$("button").length-1]);

     copy.insertBefore(button);
     }
     } else {
     for(i=choices; i>number-1; i--){
     $($(".choice")[i]).remove();
     }
     }


     if(number >= 1){
     $(".choice").show();
     } else {
     $(".choice").hide();
     }


     });
     })
     */
</script>

</body>

</html>
<%
}else {response.sendRedirect("login.jsp");} %>
