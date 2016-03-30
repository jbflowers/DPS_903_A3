package bean.questionresponse;

public interface QuestionResponseCommonBusiness {
	public void setAttempt(int attempt);
	public int getAttempt();
	
	public void setQuestionId(int id);
	public int getQuestionId();
	
	public void setQuestionResponse(String[] response);
	public String[] getQuestionResponse();
	
	public boolean isCorrect();
	
	public void commitQuestionResponse();
	public void incrementAttempts();
	
	public boolean isLastAttemptWrong();
	public void setLastAttemptWrong(boolean lastAttemptWrong);
	
	public void reset();
	
}
