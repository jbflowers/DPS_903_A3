<HTML>
<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" scope="session"/>
<% 
	questionResponse.commitQuestionResponse();
	//questionResponse.incrementAttempts();
	if (questionResponse.isCorrect()){
		quiz.nextQuestion();
		questionResponse.setLastAttemptWrong(false);
		System.out.println("Is correct!");
		questionResponse.reset();
	}
	else{
		System.out.println("Is not correct!");
		questionResponse.setLastAttemptWrong(true);
	}
	
	response.sendRedirect("quiz_question.jsp");
%>
</HTML>