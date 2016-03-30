<%@ page import="model.Answer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<HTML>
<jsp:useBean id="question" scope="session" class="bean.question.QuestionBean"/>

<%--<jsp:useBean id="answer" scope="session" class="bean.answer.AnswerBean"/>--%>
<%!
    List<Answer> tempAnswers = new ArrayList<Answer>();
    Answer tempAnswer;
%>
<%
    //numberOfChoices = Integer.parseInt(request.getParameter("numberOfChoices"));

    for(int i=0; i < question.getNumberOfChoices(); i++){
        tempAnswer = new Answer();
        System.out.println("answer text " + i);
        System.out.println(request.getParameter("choice"+i));
        System.out.println(request.getParameter("correct"+i));
        tempAnswer.setText(request.getParameter("choice"+i));

        if(request.getParameter("correct"+i) == "true"){
            tempAnswer.setIsCorrect(true);
        } else {
            tempAnswer.setIsCorrect(false);
        }

        //tempAnswer.setQuestion(question);
        tempAnswer.setQuestion(question.getQuestion());
        //tempAnswer.commitAnswer();
        tempAnswers.add(tempAnswer);
    }

    question.setAnswers(tempAnswers);

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