package bean.question;

import javax.annotation.PreDestroy;

import model.Answer;
import model.Question;

import utils.DBManager;

import java.util.List;

public class QuestionBeanBase implements QuestionCommonBusiness{
    private int numberOfChoices;
    private Question question;
    protected DBManager dbm;

    public QuestionBeanBase(){
        System.out.println("Constructing a question...");

        dbm = new DBManager();
        question = new Question();

        System.out.println("question bean constructor");

        //dbm.commitQuestion(question);
    }

    @Override
    public void setNumberOfChoices(String text){
        numberOfChoices = Integer.parseInt(text);
    }

    @Override
    public int getNumberOfChoices(){
        return numberOfChoices;
    }

    @Override
    public void setText(String text){
        question.setText(text);
    }

    @Override
    public String getText(){
        return question.getText();
    }

    @Override
    public void setType(String type){
        question.setType(type);
    }

    @Override
    public String getType(){
        return question.getType();
    }

    @Override
    public void setDifficulty(String difficulty){
        question.setDifficulty(difficulty);
    }

    @Override
    public String getDifficulty(){
        return question.getDifficulty();
    }

    @Override
    public void setHint(String hint){
        question.setHint(hint);
    }

    @Override
    public String getHint(){
        return question.getHint();
    }

    @Override
    public void setAttemptsBeforeHint(int attemptsBeforeHint){
        question.setAttemptsBeforeHint(attemptsBeforeHint);
    }

    @Override
    public int getAttemptsBeforeHint(){
        return question.getAttemptsBeforeHint();
    }

    @Override
    public void setAnswers(List<Answer> answers){
        question.setAnswers(answers);
    }

    @Override
    public List<Answer> getAnswers(){
        return question.getAnswers();
    }

    @Override
    public void commitQuestion(){
        dbm.commitQuestion(question);
    }

    @PreDestroy
    public void destroy(){
        dbm.close();
    }

}
