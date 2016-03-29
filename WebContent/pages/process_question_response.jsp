<HTML>
<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" scope="session"/>
<% 
	questionResponse.commitQuestionResponse();
	if (questionResponse.isCorrect()){
		quiz.nextQuestion();
		System.out.println("Is correct!");
	}
	else{
		System.out.println("Is not correct!");
		questionResponse.incrementAttempts();
	}
	
	Thread.sleep(5000); // sleep 1 seconds
	response.sendRedirect("quiz_question.jsp");
%>
</HTML>