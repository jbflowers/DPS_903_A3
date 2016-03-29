package controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.platform.database.DB2MainframePlatform;

import model.Question;
import model.Quiz;
import utils.DBManager;

public class QuizBeanBase implements QuizCommonBusiness{
	private int currentQuestion;
	private Quiz quiz;
	private DBManager dbm;
	
	
	public QuizBeanBase(){
		quiz = new Quiz();
		dbm = new DBManager();
		currentQuestion = 1;
		
		ArrayList<Question> questions = generateQuestions();
	}
	
	private ArrayList<Question> generateQuestions(){
		List<Question> questions = dbm.getQuestions();
		
		for(Question question: questions){
			//if()
		}
		
		
		return null;
	}
	
	@Override
	public int getCurrentQuestion() {
		// TODO Auto-generated method stub
		return currentQuestion;
	}

}
