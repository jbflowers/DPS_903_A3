<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" scope="session"/>
<%
// Check if user is logged in
boolean loggedIn = request.getSession().getAttribute("userid") != null;
if (!loggedIn){
	response.sendRedirect("login.jsp");
}
%>
<% 
	questionResponse.commitQuestionResponse();
	if (questionResponse.isCorrect()){
		quiz.nextQuestion();
		questionResponse.reset();
	}
	else{
		questionResponse.setLastAttemptWrong(true);
	}
	
	response.sendRedirect("quiz_question.jsp");
%>