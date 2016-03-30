package bean.instructor;

import model.Question;
import utils.DBManager;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class InstructorBeanBase implements InstructorCommonBusiness {
	protected List<Question> questions;
	protected DBManager dbm;
	
	public InstructorBeanBase(){
		// Initialize db manager and other fields
		dbm = new DBManager();

		// Make questions
		List<Question> questions = new ArrayList<Question>();

	}
	
	@Override
	public List<Question> getAllQuestions(){
		return questions = dbm.getQuestions();
	}

	@Override
	public void removeQuestionById(int id){
		dbm.removeQuestionById(id);
	}

	@PreDestroy
	public void destory(){
		dbm.close();
	}
}
