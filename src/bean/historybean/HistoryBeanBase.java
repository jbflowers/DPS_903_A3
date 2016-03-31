package bean.historybean;

import java.util.List;

import model.QuizResponse;
import utils.DBManager;

public class HistoryBeanBase implements HistoryCommonBusiness{
	protected DBManager dbm;

	public HistoryBeanBase(){
		dbm = new DBManager();
	}

	@Override
	public List<QuizResponse> getAllQuizResponses() {
		return dbm.getQuizResponses();
	}

}
