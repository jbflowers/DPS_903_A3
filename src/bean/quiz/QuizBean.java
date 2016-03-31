package bean.quiz;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean
public class QuizBean extends QuizBeanBase implements QuizCommonBusiness {
	
	public QuizBean() {
	
    }

}
