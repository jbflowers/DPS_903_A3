<HTML>
<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" scope="session"/>
<%
// Check if user is logged in
boolean loggedIn = request.getSession().getAttribute("userid") != null;

if (!loggedIn){
	// Redirect
	response.sendRedirect("login.jsp");
}
%>
<% 
	questionResponse.commitQuestionResponse();
	//questionResponse.incrementAttempts();
	if (questionResponse.isCorrect()){
		quiz.nextQuestion();
		questionResponse.reset();
		System.out.println("Is correct!");
		
	}
	else{
		System.out.println("Is not correct!");
		questionResponse.setLastAttemptWrong(true);
	}
	
	response.sendRedirect("quiz_question.jsp");
%>
</HTML>