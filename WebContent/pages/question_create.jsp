<%@ page import="model.Answer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.question.QuestionBean" %>
<jsp:useBean id="question" scope="session" class="bean.question.QuestionBean"/>

<% boolean loggedIn = request.getSession().getAttribute("userid") != null;

    if (!loggedIn){
        // Redirect
        response.sendRedirect("login.jsp");
    } else {
        if (session.getAttribute("role").equals("student")){
        response.sendRedirect("index.jsp");
        }
    }
    %>
<!DOCTYPE html>
<html lang="en">
<%!
    // temporary variables
    List<Answer> tempAnswers;
    Answer tempAnswer;
    int numOfChoices;
    String[] choices;
    List<Answer> oldAnswers;
%>
<%
    if (request.getParameter("text") != null && request.getParameter("edit") != null) {
        oldAnswers = new ArrayList<Answer>();
        tempAnswers = new ArrayList<Answer>();
        question.setIsEdit(false);
        // set possible choices / answers to this question
        numOfChoices = Integer.parseInt(request.getParameter("numberOfChoices"));

        choices = request.getParameterValues("choice[]");

        oldAnswers = question.getAnswers();

        System.out.println("attempting to clean bean");

        for (Answer oldAnswer : oldAnswers){
            question.removeAnswerById(oldAnswer.getId());
        }

        for (int i = 0; i < numOfChoices; i++) {
            tempAnswer = new Answer();
            tempAnswer.setText(choices[i]);

            if (request.getParameter("correct" + i).equals("true")) {
                tempAnswer.setIsCorrect(true);
            } else {
                tempAnswer.setIsCorrect(false);
            }

            tempAnswer.setQuestion(question.getQuestion());
            tempAnswers.add(tempAnswer);
        }

        System.out.println("assigning new answers");

        question.setAnswers(tempAnswers);

        // finally redirect to save the question
        response.sendRedirect("process_question_create.jsp");

    }

    if (request.getParameter("edit") != null && request.getParameter("edit").equals("true")){
        System.out.println("trying to edit the page");
        question.setQuestion(question.getQuestionById(Integer.parseInt(request.getParameter("id"))));
        question.setIsEdit(true);
    } else {
        question = new QuestionBean();
        question.setIsEdit(false);
    }

%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create a Quiz Question</title>

    <!-- Bootstrap Core CSS -->
    <link href="../bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="../dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

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

    <jsp:setProperty name="question" property="*" />
    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><% if(request.getParameter("edit") != null && request.getParameter("edit").equals("true") ) { %>
                    Edit
                    <% } else { %>
                    Create
                    <% } %>
                    a Quiz Question</h1>
                <form method="POST">
                    <div class="form-group">

                        <label for="text">Question Description</label>
                        <input type="text" name="text" id="text" placeholder="Enter problem description here..." class="form-control" value="<%=question.getText()!=null?question.getText():""%>">
                        <label for="type">Type of Question</label>
                        <select name="type" class="form-control" id="type">
                            <option value="check" <%=(question.getType()!=null&&question.getType().equals("check"))?"selected":""%>>Checkbox</option>
                            <option value="drop" <%=(question.getType()!=null&&question.getType().equals("drop"))?"selected":""%>>Dropdown</option>
                            <option value="mc" <%=(question.getType()!=null&&question.getType().equals("mc"))?"selected":""%>>Multiple Choice</option>
                            <option value="number" <%=(question.getType()!=null&&question.getType().equals("number"))?"selected":""%>>Numeric Input</option>
                            <option value="text" <%=(question.getType()!=null&&question.getType().equals("text"))?"selected":""%>>Text Input</option>
                        </select>
                        <label for="difficulty">Difficulty of Question</label>
                        <select name="difficulty" class="form-control" id="difficulty">
                            <option value="easy" <%=(question.getDifficulty()!=null&&question.getDifficulty().equals("easy"))?"selected":""%>>Easy</option>
                            <option value="medium" <%=(question.getDifficulty()!=null&&question.getDifficulty().equals("medium"))?"selected":""%>>Medium</option>
                            <option value="hard" <%=(question.getDifficulty()!=null&&question.getDifficulty().equals("hard"))?"selected":""%>>Difficult</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="hint">Hint to Display to Student: </label>
                        <input type="text" name="hint" id="hint" class="form-control" placeholder="Hint goes here..." value="<%=question.getHint()!=null?question.getHint():""%>">
                        <label for="attemptsBeforeHint">Number of Attempts Before a Hint is Displayed: </label>
                        <input type="number" name="attemptsBeforeHint" id="attemptsBeforeHint" class="form-control" placeholder="Number of Attempts (ie. '3')..." value="<%=question.getAttemptsBeforeHint()%>">
                    </div>

                    <div class="form-group oneMany manyMany">
                        <label for="numberOfChoices">Number of Possible Choices?</label>
                        <input type="number" name="numberOfChoices" id="numberOfChoices" class="form-control" placeholder="Enter the number of choices (ie. '5')..." value="<%=question.getAnswers().size()%>">
                    </div>


                    <div class="form-group choice" style="display:none">
                        <label for="choice0">Choice #1: </label>
                        <input type="text" name="choice[]" id="choice0" class="form-control">
                        <label class="radio-inline">
                            <input type="radio" name="correct0" value="true"> Correct
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="correct0" value="false"> Incorrect
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <% if(request.getParameter("edit") != null && request.getParameter("edit").equals("true") ) { %>
                        Edit
                        <% } else { %>
                        Create
                        <% } %>
                        New Quiz Question</button>
                    <a href="instructor_table.jsp" class="btn btn-default">Back to List of Questions</a>
                </form>
                <%--<jsp:getProperty name="quiz" property="currentQuestion"/>--%>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->


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
<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

<!-- Custom Form Code -->
<script>
    $(function() {

        function checkChoiceNumber(repopulate){
            console.log("checking number of choices...");
            var number = parseInt($("#numberOfChoices").val()), i=0, choices = $(".choice").length, copy, button, answers=[], correct=[];

            <%

             for(Answer answer: question.getAnswers()){
            %>
                answers.push("<%=answer.getText()%>");
                correct.push("<%=answer.getIsCorrect()%>");
            <%
             }

            %>


            if(choices < number){
                for(i=choices; i<number; i++){

                    copy = $($(".choice")[0]).clone(true, true);

                    // Label for Choice...
                    $(copy.find("label")[0]).attr("for", "choice"+(i)).html("Choice #"+(i+1)+(": "));

                    // id for choice input
                    $(copy.find("input")[0]).attr("id", "choice"+(i)).val("");

                    // correct radio
                    $(copy.find("input")[1]).attr("name", "correct"+(i))
                            .removeAttr("selected").prop("checked", false);

                    // incorrect radio
                    $(copy.find("input")[2]).attr("name", "correct"+(i))
                            .removeAttr("selected").prop("checked", false);

                    button = $($("button")[$("button").length-1]);

                    copy.insertBefore(button);
                }
            } else {
                for(i=choices; i>number-1 && i > 0 ; i--){
                    $($(".choice")[i]).remove();
                }
            }


            if(number >= 1){
                $(".choice").show();
            } else {
                $(".choice").hide();
            }

            for(i=0; i<number && repopulate; i++){
                if(answers[i]){
                    $($($(".choice")[i]).find("input")[0]).val(answers[i]);
                }

                if(correct[i]){
                    if(correct[i] === "true"){
                        $($($(".choice")[i]).find("input")[1]).attr("selected", "true");
                        $($($(".choice")[i]).find("input")[1]).prop("checked", "true");
                    } else {
                        $($($(".choice")[i]).find("input")[2]).attr("selected", "true");
                        $($($(".choice")[i]).find("input")[2]).prop("checked", "true");
                    }
                }
            }

        }


        $('#type').change(function(){
            switch( $(this).val()){
                case "mc" || "drop":
                    console.log("mc or dropdown");
                    break;
                case "number" || "text":
                    console.log("num or txt");
                    break;
                case "check":
                    console.log("checkbox");
                    break;
                default:
                    console.log("default nada");
            }

        });

        checkChoiceNumber(true);

        $('#numberOfChoices').on("keyup change", checkChoiceNumber);
    })
</script>

</body>

</html>
