package bean.historybean;

import java.util.List;

import model.QuizResponse;
import model.User;
import utils.DBManager;

public class HistoryBeanBase implements HistoryCommonBusiness{
	protected DBManager dbm;
	protected User user;
	
	public HistoryBeanBase(){
		dbm = new DBManager();
	}

	@Override
	public List<QuizResponse> getAllQuizResponses() {
		return dbm.getQuizResponses();
	}

	@Override
	public List<QuizResponse> getQuizResponsesForUser() {
		return dbm.getQuizResponsesForUser(user);
	}

	@Override
	public void setUser(String userId) {
		user = dbm.getUserByEmail(userId);
	}
	
	

}
