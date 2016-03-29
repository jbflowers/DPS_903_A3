package bean.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import model.Question;
import model.Quiz;
import utils.DBManager;

public class QuizBeanBase implements QuizCommonBusiness{
	private int currentQuestionNumber;
	protected Quiz quiz;
	protected DBManager dbm;
	
	public QuizBeanBase(){
		System.out.println("MADE IT HERE YAY");
		
		// If we have a quiz already, exit
		if (quiz != null){
			return;
		}
		
		// Initialize db manager and other fields
		dbm = new DBManager();
		currentQuestionNumber = 1;
		
		// Make your quiz
		quiz = new Quiz();
		
		// Make questions
		List<Question> questions = new ArrayList<Question>();

		System.out.println("Id be interested to know");
		
		// Make three easy questions
		while(questions.size() != 3){
			Question easyQuestion = dbm.getRandomEasyQuestion();
			if (!questions.contains(easyQuestion)){
				questions.add(easyQuestion);
			}
		}
		
		// Make two unique medium questions
		while(questions.size() != 5){
			Question mediumQuestion = dbm.getRandomMediumQuestion();
			if (!questions.contains(mediumQuestion)){
				questions.add(mediumQuestion);
			}
		}
		
		// Make one hard question
		questions.add(dbm.getRandomHardQuestion());
		
		// Set fields
		quiz.setQuestions(questions);
		quiz.setAllowHints(true);
		quiz.setDescription("An automated quiz made for you!");
		quiz.setName("Your quiz");
		
		// Store it
		dbm.commitQuiz(quiz);
	}
	
	@Override
	public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
		
	}

	@Override
	public Question getCurrentQuestion() {
		return quiz.getQuestions().get(currentQuestionNumber);
	}
	
	@PreDestroy
	public void destory(){
		dbm.close();
	}

}
