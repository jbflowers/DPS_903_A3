<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean" scope="session"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean" scope="session"/>

<!DOCTYPE html>
<html lang="en">

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
	
	Thread.sleep(1000); // sleep 1 seconds
	
%>
<meta http-equiv="refresh" content="0; url=quiz_question.jsp" />
</html>
