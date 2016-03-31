<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" scope="session"/>
<!DOCTYPE html>
<html lang="en">

<%
// Check if user is logged in
boolean loggedIn = request.getSession().getAttribute("userid") != null;

if (!loggedIn){
	// Redirect
	response.sendRedirect("login.jsp");
}
%>

<% 
	if (quiz.getCurrentQuestionNumber() != 7){
		response.sendRedirect("quiz_question.jsp");
	}
%>


<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Quiz Results</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="../dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../bower_components/morrisjs/morris.css" rel="stylesheet">

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

        <%--Navbar--%>
        <jsp:include page="navbar.jsp" />

		<jsp:setProperty name="questionResponse" property="questionResponse" /> 
        <div id="page-wrapper">
 			<% 
				quiz.completeQuiz();
		
 			%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Quiz Results</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
            	<div class="col-lg-6">
		            <% if (quiz.getQuizResponse().getMark() == 6){ %>
			           	 
			           	 <div class="panel panel-green">
				             <div class="panel-heading">
				                 Success!
				             </div>
				             <div class="panel-body">
				                 <p>You got 6/6! 100%!</p>
				             </div>
				             <div class="panel-footer">
				                 Great work!
				             </div>
				         </div>
				         
		            <%} %>
		            <% if (quiz.getQuizResponse().getMark() < 6 && quiz.getQuizResponse().getMark() > 2){ %>
			           	 
			           	 <div class="panel panel-yellow">
				             <div class="panel-heading">
				                 Not bad!
				             </div>
				             <div class="panel-body">
				                 <p>You got <%= quiz.getQuizResponse().getMark() %>/6.</p>
				             </div>
				             <div class="panel-footer">
				                 Needs some improvement.
				             </div>
				         </div>
				         
		            <%} %>
		            <% if (quiz.getQuizResponse().getMark() < 3){ %>
			           	 
			           	 <div class="panel panel-red">
				             <div class="panel-heading">
				                 You failed!
				             </div>
				             <div class="panel-body">
				                 <p>You got <%= quiz.getQuizResponse().getMark() %>/6.</p>
				             </div>
				             <div class="panel-footer">
				                 Needs a lot of work!
				             </div>
				         </div>
				         
		            <%} %>
            	</div>
            </div>
			           	
		                <% 
		                	quiz.reset();
		                	questionResponse.reset();
		                %>
	                </div>
	                <!-- /.panel panel-primary -->
	            </div>
	            <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
           
            <div class="panel-footer">
            </div>
            <!-- /.panel-footer -->   
        
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
    <script src="../bower_components/morrisjs/morris.min.js"></script>
    <script src="../js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

</body>

</html>
