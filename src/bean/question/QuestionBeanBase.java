package bean.question;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.Question;

import utils.DBManager;

public class QuestionBeanBase implements QuestionCommonBusiness{
    //private int currentQuestionNumber;
    protected Question question;
    protected DBManager dbm;
	private EntityManager em;
    
    
    public QuestionBeanBase(){
        System.out.println("Constructing a question...");

        // If we have a question already, exit
        if (question != null){
            return;
        }

        // Initialize db manager and other fields
        dbm = new DBManager();
        //currentQuestionNumber = 1;

        // Make your quiz
        question = new Question();

        // Make questions
        //List<Question> questions = new ArrayList<Question>();

        System.out.println("Id be interested to know");


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
