package bean.question;

import javax.annotation.PreDestroy;

import model.Question;

import utils.DBManager;

public class QuestionBeanBase implements QuestionCommonBusiness{
    //private int currentQuestionNumber;
    private Question question;
    protected DBManager dbm;

    public QuestionBeanBase(){
        System.out.println("Constructing a question...");

        dbm = new DBManager();
        question = new Question();

        System.out.println("question bean constructor");


        // Make two unique medium questions
//        while(questions.size() != 5){
//            Question mediumQuestion = dbm.getRandomMediumQuestion();
//            if (!questions.contains(mediumQuestion)){
//                questions.add(mediumQuestion);
//            }
//        }

        // Make one hard question
//        questions.add(dbm.getRandomHardQuestion());

        // Set fields
//        quiz.setQuestions(questions);
//        quiz.setAllowHints(true);
//        quiz.setDescription("An automated quiz made for you!");
//        quiz.setName("Your quiz");

        // Store it
        dbm.commitQuestion(question);
    }

    //@Override
    //public int getQuestionForm() {
        //return currentQuestionNumber;
    //}

    //@Override
    //public Question getCurrentQuestion() {
    //   return quiz.getQuestions().get(currentQuestionNumber);
    //}

    @PreDestroy
    public void destroy(){
        dbm.close();
    }

}
