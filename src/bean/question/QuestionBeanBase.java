package bean.question;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.Answer;
import model.Question;

import utils.DBManager;

import java.util.List;

public class QuestionBeanBase implements QuestionCommonBusiness{
    private int numberOfChoices;
    private Question question;
    protected DBManager dbm;
	private EntityManager em;
    private boolean isEdit;
    
    public QuestionBeanBase(){
        System.out.println("Constructing a question...");

        dbm = new DBManager();
        question = new Question();

        System.out.println("question bean constructor");

        //dbm.commitQuestion(question);
    }

    @Override
    public Question getQuestionById(int id){
        return dbm.getQuestionById(id);
    }

    public void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }

    public boolean getIsEdit(){
        return isEdit;
    }

    @Override
    public Question getQuestion(){
        return question;
    }

    @Override
    public void setQuestion(Question question){
        this.question = question;
    }

    @Override
    public void populateQuestionById(int id) {
        question = dbm.getQuestionById(id);
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
    public void removeAnswerById(int id){
        dbm.removeAnswerById(id);
    }

    @Override
    public void commitQuestion(){
        dbm.commitQuestion(question);
    }

    @Override
    public void updateQuestion() { dbm.updateQuestion(question); }

    @PreDestroy
    public void destroy(){
        dbm.close();
    }

}
