package bean.question;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Question;
import utils.DBManager;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean
public class QuestionBean extends QuestionBeanBase implements QuestionCommonBusiness {

    public QuestionBean() {

    }

}
