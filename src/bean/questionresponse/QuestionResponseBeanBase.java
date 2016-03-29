package bean.questionresponse;

import java.util.List;

import javax.annotation.PreDestroy;

import model.Answer;
import model.Question;
import model.QuestionResponse;
import utils.DBManager;

public class QuestionResponseBeanBase implements QuestionResponseCommonBusiness{
	private QuestionResponse questionResponse;
	private int attempts;
	protected DBManager dbm;
	
	
	
	public QuestionResponseBeanBase(){
		// Initialize db manager and other fields
		dbm = new DBManager();
		questionResponse = new QuestionResponse();
	}
	
	@PreDestroy
	public void destory(){
		dbm.close();
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
		Question question = dbm.getQuestionById(questionResponse.getQuestionId());
		
		List<Answer> answers = question.getAnswers();
		String type = question.getType();
		
		System.out.println("QuestionResponse in QuestionResponseBean.isCorrect(): ");
		for(int i = 0; i < questionResponse.getQuestionResponse().length; i++){
			System.out.println("QuestionResponse.questionResponse: " + questionResponse.getQuestionResponse()[i]);
		}
		
		System.out.println("Answers in QuestionResponseBean.isCorrect(): ");
		for(int i = 0; i < answers.size(); i++){
			System.out.println("Answer text 1:" + answers.get(i).getText());
		}
		
		// If the question is text or number, the question response is a value to match
		if (type.equals("text") || type.equals("number")){
			for(Answer answer: answers){
				if (answer.getText().equalsIgnoreCase(questionResponse.getQuestionResponse()[0]) && answer.getIsCorrect()){
					return true;
				}
			}
		}
		// Really no idea what this implies, so lets ignore it for now
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
		// Otherwise, question response is an id to the answer
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
		attempts++;
	}
	
	

}
