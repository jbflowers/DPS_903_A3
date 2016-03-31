package bean.historybean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean
public class HistoryBean extends HistoryBeanBase implements HistoryCommonBusiness {
	
	public HistoryBean() {
	
    }

}
