<HTML>
<jsp:useBean id="question" scope="session" class="bean.question.QuestionBean"/>

<%--<jsp:useBean id="answer" scope="session" class="bean.answer.AnswerBean"/>--%>

<%
    //numberOfChoices = Integer.parseInt(request.getParameter("numberOfChoices"));

    System.out.println("inside question create after post");
    System.out.println(question.getText());
    System.out.println(question.getType());
    System.out.println(question.getDifficulty());

    question.commitQuestion();
//    questionResponse.commitQuestionResponse();
//    if (questionResponse.isCorrect()){
//        quiz.nextQuestion();
//        Syste
// m.out.println("Is correct!");
//    }
//    else{
//        System.out.println("Is not correct!");
//        questionResponse.incrementAttempts();
//    }
//
//    Thread.sleep(5000); // sleep 1 seconds
//    response.sendRedirect("quiz_question.jsp");

%>
</HTML>