package bean.questionresponse;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean
public class QuestionResponseBean extends QuestionResponseBeanBase implements QuestionResponseCommonBusiness {
	
	public QuestionResponseBean() {
	
    }

}
