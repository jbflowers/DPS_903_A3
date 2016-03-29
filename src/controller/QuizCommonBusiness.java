package controller;

import model.Question;

public interface QuizCommonBusiness {
	int getCurrentQuestionNumber();
	Question getCurrentQuestion();
}
