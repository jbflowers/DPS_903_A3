package bean.historybean;

import java.util.List;
import model.QuizResponse;

public interface HistoryCommonBusiness {
	public List<QuizResponse> getAllQuizResponses();
	public List<QuizResponse> getQuizResponsesForUser();
	public void setUser(String userId);
}
