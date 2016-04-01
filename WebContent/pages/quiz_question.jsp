<jsp:useBean id="quiz" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" />

<%
// Check if user is logged in

boolean loggedIn = request.getSession().getAttribute("userid") != null;

if (loggedIn){
	String userId = (String) request.getSession().getAttribute("userid");
	quiz.setUser(userId);
}
else{
	response.sendRedirect("login.jsp");
}

%>

<!DOCTYPE html>
<html lang="en">
<% 

	// If the current question is 0, then start the bean;
	if(quiz.getCurrentQuestionNumber() == 1 && questionResponse.getAttempt() == 0){
		quiz.start();
	}
	
	// Determine whether we've been provided empty input or a wrong answer
	boolean emptyInput = false;
	boolean failedAttempt = questionResponse.isLastAttemptWrong();	
	boolean redirect = false;
	
	// If the quiz is done, go to results
	if (quiz.getCurrentQuestionNumber() == 7){
		response.sendRedirect("quiz_results.jsp");
	}
	
	// If this is not their first attempt and we got nothing, they provided empty input
	if (questionResponse.getAttempt() > 0 && (request.getParameter("questionResponse") == null || request.getParameter("questionResponse").length() == 0)){
		emptyInput = true;
	}
	// Otherwise we got an answer, so we should process it
	else if (questionResponse.getAttempt() != 0){
		response.sendRedirect("process_question_response.jsp");
		redirect = true;
	}
	
	// However, if their last attempt was wrong, it was not empty input, we were just redirected
	if (questionResponse.isLastAttemptWrong()){
		emptyInput = false;
		questionResponse.setLastAttemptWrong(false);
	}
	
	// Finally if we weren't redirected, increment attempts
	if (!redirect)
		questionResponse.incrementAttempts();
	
%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Quiz Question <jsp:getProperty name="quiz" property="currentQuestionNumber"/> </title>

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
 				// Get all necessary information for this page
 				int questionNumber = quiz.getCurrentQuestionNumber();
 				String questionText = quiz.getCurrentQuestion().getText();
				String questionType = quiz.getCurrentQuestion().getType();
				String[] answerIds = new String[quiz.getCurrentQuestion().getAnswers().size()];
				String[] answerTexts = new String[answerIds.length];
				
				// Fill answerIds and answerTexts
				for(int i = 0; i < quiz.getCurrentQuestion().getAnswers().size(); i++){
					answerIds[i] =   new Integer(quiz.getCurrentQuestion().getAnswers().get(i).getId()).toString();
					answerTexts[i] =   quiz.getCurrentQuestion().getAnswers().get(i).getText();
				}

				questionResponse.setQuestionId(quiz.getCurrentQuestion().getId());
 			%>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Quiz Question <%= questionNumber %></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            
            <% // Handles validation of empty input and hints %>
            <% if (emptyInput){ %>
            <div class="row">
            	<div class="col-lg-12">
	            	<div class="alert alert-danger">
	                	You cannot submit an empty answer!
	                </div>
                </div>
            </div>
            <%} 
            	System.out.println("Question's attempts before hint: " + quiz.getCurrentQuestion().getAttemptsBeforeHint());
            %>
            
         	<% if (failedAttempt && questionResponse.getAttempt() < quiz.getCurrentQuestion().getAttemptsBeforeHint()){ %>
	            <div class="row">
	            	<div class="col-lg-12">
		            	<div class="alert alert-warning">
		                	Uh oh, looks like that's not the right answer. Try again!
		                </div>
	                </div>
	            </div>
            <%} else if (questionResponse.getAttempt() >= quiz.getCurrentQuestion().getAttemptsBeforeHint()) { %>
	            <div class="row">
	            	<div class="col-lg-12">
		            	<div class="alert alert-warning">
		                	Psst! Here's a hint: <%= quiz.getCurrentQuestion().getHint() %>
		                </div>
	                </div>
	            </div>
            <%} %>
            <% // Main question starts here %>
            <div class="row">
            	<div class="col-lg-6">
					<div class="panel panel-primary">
		                <div class="panel-heading">
		                    <%= questionText %>
		                </div>
			           	<form method="POST">
			                <div class="panel-body">
			                    <% if (questionType.equals("text")){ %>
			                    
			                    <div class="form-group">
	                                <label></label>
	                                <input class="form-control" placeholder="Enter text" name="questionResponse">
	                            </div>    
			                    
			                    <%}
			                    else if (questionType.equals("number")){ %>
			                    
			                    <div class="form-group">
		                            <input class="form-control" type="number" name="questionResponse">
		                        </div>
			                    
			                    <%}
			                    else if (questionType.equals("mc")){ %>
			                    
			                    <div class="form-group">
		                            <div class="radio">
		                                <%
		                                
		                                for(int i = 0; i < answerIds.length; i++){ %>
		                                
		                                <label>
		                                    <input name="questionResponse" id="<%= answerIds[i] %>" value="<%= answerIds[i] %>" <% if(i == 0) out.print("checked=''"); %> type="radio"><%= answerTexts[i] %>
		                                </label>
		                                <br>
		                                <%} %>
		                            </div>
		                        </div>
			                    
			                    <%} 
			                    else if (questionType.equals("check")){ %> 
			                    
	                    		<div class="form-group">
	                                
	                                <label>Checkboxes</label>
	                                <div class="checkbox">
	                                <%  for(int i = 0; i < answerIds.length; i++){ %>
	                                    <label>
	                                        <input name="questionResponse" value="<%= answerIds[i] %>" type="checkbox"><%= answerTexts[i] %>
	                                    </label>
	                                    <br>
	                                <%} %>
	                                </div>
	                            </div>
	                            
			                    <%}
			                    else if(questionType.equals("drop")){ %>
			                      
			                    <div class="form-group">
		                            <label>Selects</label>
		                            <select class="form-control" name="questionResponse">
		                            	<% for(int i = 0; i < answerIds.length; i++){ %>
			                            	<option value="<%= answerIds[i] %>"><%= answerTexts[i] %></option>
			                           	<%} %>
		                            </select>
		                        </div>
			                    
			                    <%}
			                    %>
			                    
			                    <p>
			
			                    </p>
			                </div>
			                <div class="panel-footer">
								<button type="submit" class="btn btn-default submit-button">Submit</button>
			                </div>
		                </form>
		                
	                </div>
	                <!-- /.panel panel-primary -->
	            </div>
	            <!-- /.col-lg-6 -->
	           <div class="col-lg-2">
	            </div>
	            <div class="col-lg-4">
					<div class="panel panel-red">
                        <div class="panel-heading">
                           	Time Left
                        </div>
                        <div id="timer_div" class="panel-body">
                           20:00 
                        </div>
                        <div class="panel-footer">
                        </div>
                    </div>
	            </div>
	            
	            
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

	<script>
	var seconds_left = <%= quiz.getTimeLeft() %>;
	var interval = setInterval(function() {
	    seconds_left--;
	    
		var minutes = parseInt(seconds_left / 60);
		var seconds = parseInt(seconds_left - (minutes * 60));
		
		if (seconds < 10){
			seconds = String("0" + seconds);
		}
		
		document.getElementById('timer_div').innerHTML = minutes + ":" + seconds

	    if (seconds_left <= 0)
	    {
	    	window.location.href = "quiz_timeout.jsp";
	    }
	    
	}, 1000);
	</script>

</body>

</html>
