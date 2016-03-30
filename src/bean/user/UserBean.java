package bean.user;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bean.user.UserBeanBase;
import bean.user.UserCommonBusiness;
import model.User;
import utils.DBManager;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean

public class UserBean extends UserBeanBase implements UserCommonBusiness {

    public UserBean() {

    }

}