package bean.question;

import model.Question;
import model.Answer;
import java.util.List;

public interface QuestionCommonBusiness {

    void populateQuestionById(int id);

    int getNumberOfChoices();
    void setNumberOfChoices(String text);

    Question getQuestionById(int id);

    Question getQuestion();
    void setQuestion(Question question);

    void setText(String text);
    String getText();

    void setType(String type);
    String getType();

    void setDifficulty(String difficulty);
    String getDifficulty();

    void setHint(String hint);
    String getHint();

    void setAttemptsBeforeHint(int attemptsBeforeHint);
    int getAttemptsBeforeHint();

    void setAnswers(List<Answer> answers);
    List<Answer> getAnswers();

    void commitQuestion();
}
