package bean.questionresponse;

import java.util.List;

import model.Answer;
import model.Question;
import model.QuestionResponse;
import utils.DBManager;

public class QuestionResponseBeanBase implements QuestionResponseCommonBusiness{
	private QuestionResponse questionResponse;
	protected DBManager dbm;
	private boolean lastAttemptWrong = false;
	
	public QuestionResponseBeanBase(){
		// Initialize db manager and other fields
		dbm = new DBManager();
		questionResponse = new QuestionResponse();
	}

	@Override
	public void setAttempt(int attempt) {
		questionResponse.setAttempt(attempt);
	}

	@Override
	public int getAttempt() {
		return questionResponse.getAttempt();
	}

	@Override
	public void setQuestionId(int id) {
		questionResponse.setQuestionId(id);
	}

	@Override
	public int getQuestionId() {
		return questionResponse.getQuestionId();
	}

	@Override
	public void setQuestionResponse(String[] response) {
		questionResponse.setQuestionResponse(response);
	}

	@Override
	public String[] getQuestionResponse() {
		return questionResponse.getQuestionResponse();
	}

	@Override
	public boolean isCorrect() {
		// Commit this response
		dbm.commitQuestionResponse(questionResponse);
		
		// Get the question and its answers
		Question question = dbm.getQuestionById(questionResponse.getQuestionId());
		
		List<Answer> answers = question.getAnswers();
		String type = question.getType();
		
		// If the question is text or number, the question response is a value to match
		if (type.equals("text") || type.equals("number")){
			for(Answer answer: answers){
				if (answer.getText().equalsIgnoreCase(questionResponse.getQuestionResponse()[0]) && answer.getIsCorrect()){
					return true;
				}
			}
		}
		// If the question is check, check each answer individually
		else if (type.equals("check")){
			String[] responses = questionResponse.getQuestionResponse();
			int answerCount = 0;
			
			for(Answer answer : answers){
				if (answer.getIsCorrect()){
					answerCount++;
				}
			}
			
			int count = 0;
			for(int i = 0; i < responses.length; i++){
				Answer answer = dbm.getAnswerById(new Integer(responses[i]));
				if (answer.getIsCorrect()){
					count++;
				}
				else{
					return false;
				}
			}
			if(count == answerCount){
				return true;
			}
		}
		// Otherwise, question response is an id to the answer chosen
		else {
			int answerId = new Integer(questionResponse.getQuestionResponse()[0]);
			Answer answer = dbm.getAnswerById(answerId);
			if (answer.getIsCorrect()){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void commitQuestionResponse() {
		dbm.commitQuestionResponse(questionResponse);
	}

	@Override
	public void incrementAttempts() {
		questionResponse.setAttempt(questionResponse.getAttempt()+1);
	}
	
	@Override
	public boolean isLastAttemptWrong() {
		return lastAttemptWrong;
	}

	@Override
	public void setLastAttemptWrong(boolean lastAttemptWrong) {
		this.lastAttemptWrong = lastAttemptWrong;
	}

	@Override
	public void reset() {
		// Reset bean to default state
		questionResponse.setIsCorrect(true);
		dbm.commitQuestionResponse(questionResponse);
		questionResponse = null;
		questionResponse = new QuestionResponse();
		lastAttemptWrong = false;
	}

	@Override
	public void setIsCorrect(boolean isCorrect) {
		questionResponse.setIsCorrect(isCorrect);
	}

	@Override
	public boolean getIsCorrect() {
		return questionResponse.getIsCorrect();
	}
}
