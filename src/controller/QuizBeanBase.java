package controller;

import org.eclipse.persistence.platform.database.DB2MainframePlatform;

import model.Quiz;
import utils.DBManager;

public class QuizBeanBase implements QuizCommonBusiness{
	private int currentQuestion;
	private Quiz quiz;
	private DBManager dbm;
	
	
	public QuizBeanBase(){
		quiz = new Quiz();
		dbm = new DBManager();
	}
	
	@Override
	public int getCurrentQuestion() {
		// TODO Auto-generated method stub
		return currentQuestion;
	}

}
