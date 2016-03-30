package bean.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.Answer;
import model.Question;
import model.QuestionResponse;
import model.Quiz;
import model.QuizResponse;
import utils.DBManager;

public class QuizBeanBase implements QuizCommonBusiness{
	private int currentQuestionNumber;
	protected Quiz quiz;
	protected DBManager dbm;
	private EntityManager em;
	private QuizResponse quizResponse;
	
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
		List<Question> tempQuestions = new ArrayList<Question>();

		System.out.println("Id be interested to know");
		
		boolean containsQuestion;
		// Make three easy questions
		while(tempQuestions.size() != 3){
			containsQuestion = false;
			Question easyQuestion = dbm.getRandomEasyQuestion();
			
			for(Question question : tempQuestions){
				if(question.equals(easyQuestion)){
					containsQuestion = true;
				}
			}
			
			if (!containsQuestion){
				tempQuestions.add(easyQuestion);
			}
		}
		
		// Make two unique medium questions
		while(tempQuestions.size() != 5){
			containsQuestion = false;
			Question mediumQuestion = dbm.getRandomMediumQuestion();
			
			for(Question question : tempQuestions){
				if(question.equals(mediumQuestion)){
					containsQuestion = true;
				}
			}
			
			if (!containsQuestion){
				tempQuestions.add(mediumQuestion);
			}
		}
		
		// Make one hard question
		tempQuestions.add(dbm.getRandomHardQuestion());
		
		// Make deep copies for all questions
		List<Question> finalQuestions = new ArrayList<Question>();
		for(Question curQuestion : tempQuestions){
			finalQuestions.add(curQuestion.copy());
		}
		
		// Set fields
		quiz.setQuestions(finalQuestions);
		quiz.setAllowHints(true);
		quiz.setDescription("An automated quiz made for you!");
		quiz.setName("Your quiz");
		
		// Store quiz
		dbm.commitQuiz(quiz);
	}
	
	@Override
	synchronized public int getCurrentQuestionNumber() {
		return currentQuestionNumber;
		
	}

	@Override
	synchronized public Question getCurrentQuestion() {
		if (currentQuestionNumber < 6){
			return quiz.getQuestions().get(currentQuestionNumber - 1);
		}
		else{
			return quiz.getQuestions().get(5);
		}
	}
	
	@PreDestroy
	public void destory(){
		dbm.close();
	}

	@Override
	synchronized public void nextQuestion() {
		currentQuestionNumber++;
	}

	@Override
	public void completeQuiz() {
		quiz.setStatus("complete");
		dbm.commitQuiz(quiz);

		int mark = 0;
		
		for(Question question : quiz.getQuestions()){
			int correctAnswers = 0;
			int correctAttempts = 0;
			
			// Calculate number of correct answers
			for (Answer answer : question.getAnswers()){
				if(answer.getIsCorrect()){
					correctAnswers++;
				}
			}
			System.out.println("___________________________________");

			// Calculate number of correct attempts
			List<QuestionResponse> questionResponses = dbm.getQuestionResponsesQuestion(question);
			for(QuestionResponse questionResponse : questionResponses){
				
				System.out.println("QUESTIONRESPONSE ISCORRECT: " + questionResponse.getIsCorrect());
				
				
				System.out.println("QUIZ RESPONSES: ");
				for(int i = 0; i < questionResponse.getQuestionResponse().length; i++){
					System.out.println("QUIZ RESPONSE: " + questionResponse.getQuestionResponse()[i]);
				}
				System.out.println("QUIZ RESPONSE ATTEMPTS: " + questionResponse.getAttempt());
				
				if(questionResponse.getIsCorrect() && questionResponse.getAttempt() == 1){
					correctAttempts+=questionResponse.getQuestionResponse().length;
				}
			}
			
			System.out.println("CORRECT ATTEMPTS " + correctAttempts);
			System.out.println("CORRECT ANSWERS " + correctAnswers);
			
			if (correctAttempts == correctAnswers){
				System.out.println("Mark was: " + mark);
				mark++;
				System.out.println("Mark is: " + mark);
			}
			
		}
		
		quizResponse = new QuizResponse();
		
		System.out.println("MARK IS: " + mark);
		quizResponse.setMark(mark);
		quizResponse.setQuiz(quiz);
		
		dbm.commitQuizResponse(quizResponse);
	}
	
	public QuizResponse getQuizResponse(){
		return quizResponse;
	}

}
