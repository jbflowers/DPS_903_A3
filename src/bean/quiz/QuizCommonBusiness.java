package bean.quiz;

import model.Question;
import model.QuizResponse;

public interface QuizCommonBusiness {
	int getCurrentQuestionNumber();
	Question getCurrentQuestion();
	void nextQuestion();
	void completeQuiz();
	public QuizResponse getQuizResponse();
	public void setUser(String email);
}
