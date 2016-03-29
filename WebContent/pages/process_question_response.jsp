<jsp:useBean id="quiz" scope="session" class="bean.quiz.QuizBean"/>
<jsp:useBean id="questionResponse" scope="session" class="bean.questionresponse.QuestionResponseBean"/>

<!DOCTYPE html>
<html lang="en">

<% 
	questionResponse.commitQuestionResponse();
	if (questionResponse.isCorrect()){
		quiz.nextQuestion();
	}
	else{
		questionResponse.incrementAttempts();
	}
	
	
%>
<jsp:forward page="quiz_question.jsp" />
</html>
