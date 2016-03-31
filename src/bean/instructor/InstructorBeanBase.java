package bean.instructor;

import model.Question;
import utils.DBManager;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class InstructorBeanBase implements InstructorCommonBusiness {
	protected DBManager dbm;
	
	public InstructorBeanBase(){
		// Initialize db manager and other fields
		dbm = new DBManager();

	}
	
	@Override
	public List<Question> getAllQuestions(){
		dbm.close();
		dbm = new DBManager();
		return dbm.getQuestions();
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
