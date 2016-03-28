package controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean
public class QuizBean extends QuizBeanBase implements QuizCommonBusiness {
	
    public QuizBean() {
    }

}
