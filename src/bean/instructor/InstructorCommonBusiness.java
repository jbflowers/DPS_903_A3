package bean.instructor;

import model.Question;

import java.util.List;

public interface InstructorCommonBusiness {
	List<Question> getAllQuestions();

	void removeQuestionById(int id);
	//Question getQuestionById(int id);
}
