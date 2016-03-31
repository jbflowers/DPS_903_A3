package bean.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import model.Answer;
import model.Question;
import model.QuestionResponse;
import model.Quiz;
import model.QuizResponse;
import model.User;
import utils.DBManager;

public class QuizBeanBase implements QuizCommonBusiness{
	private int currentQuestionNumber;
	protected Quiz quiz;
	protected DBManager dbm;
	private QuizResponse quizResponse;
	private User user;
	
	public QuizBeanBase(){
		// Initialize db manager and other fields
		dbm = new DBManager();
		currentQuestionNumber = 1;
		
		// Make your quiz
		quiz = new Quiz();
		quizResponse = new QuizResponse();
		
		// In order to be here in the first place, you must be logged in, so set user
		quizResponse.setUser(user);

		// Make questions
		List<Question> tempQuestions = new ArrayList<Question>();
		
		// Make three easy questions
		boolean containsQuestion;
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
			Question temp = curQuestion.copy();
			temp.setUsedInQuiz(true);
			finalQuestions.add(temp);
			
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
		// Set the status of the quiz and commit
		quiz.setStatus("complete");
		dbm.commitQuiz(quiz);

		// Calculate mark
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

			// Calculate number of correct attempts
			List<QuestionResponse> questionResponses = dbm.getQuestionResponsesQuestion(question);
			for(QuestionResponse questionResponse : questionResponses){
				if(questionResponse.getIsCorrect() && questionResponse.getAttempt() == 1){
					correctAttempts+=questionResponse.getQuestionResponse().length;
				}
			}
			
			if (correctAttempts == correctAnswers){
				mark++;
			}
			
		}

		quizResponse.setMark(mark);
		quizResponse.setQuiz(quiz);
		
		dbm.commitQuizResponse(quizResponse);
	}
	
	public QuizResponse getQuizResponse(){
		return quizResponse;
	}

	@Override
	public void setUser(String email) {
		if (user == null){
			user = dbm.getUserByEmail(email);
		}
	}
	
	
	

}
