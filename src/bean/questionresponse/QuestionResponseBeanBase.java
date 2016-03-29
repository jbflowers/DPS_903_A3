package bean.questionresponse;

import javax.annotation.PreDestroy;

import model.QuestionResponse;
import utils.DBManager;

public class QuestionResponseBeanBase implements QuestionResponseCommonBusiness{
	private QuestionResponse questionResponse;
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
	public void setQuestionResponse(String response) {
		questionResponse.setQuestionResponse(response);
	}

	@Override
	public String getQuestionResponse() {
		return questionResponse.getQuestionResponse();
	}

}
