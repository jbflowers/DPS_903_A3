package bean.quiz;

import model.Question;
import model.QuizResponse;

public interface QuizCommonBusiness {
	public int getCurrentQuestionNumber();
	public Question getCurrentQuestion();
	public void nextQuestion();
	public void completeQuiz();
	public QuizResponse getQuizResponse();
	public void setUser(String email);
	public long getTimeLeft();
	public void timeoutQuiz();
	public void reset();
}
