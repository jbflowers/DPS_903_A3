<HTML>
<jsp:useBean id="question" scope="session" class="bean.question.QuestionBean"/>
<%
    System.out.println("inside question create after post");
    System.out.println(question.getText());
    System.out.println(question.getType());
    System.out.println(question.getDifficulty());

    if(question.getIsEdit() == true){
        question.setIsEdit(false);
        question.updateQuestion();
    } else {
        question.commitQuestion();
    }

    response.sendRedirect("instructor_table.jsp");
%>
</HTML>