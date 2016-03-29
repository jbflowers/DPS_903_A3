package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import model.Question;
import model.Quiz;
import utils.DBManager;

/**
 * Session Bean implementation class QuizBean
 */
@Stateless
@LocalBean
public class QuizBean extends QuizBeanBase implements QuizCommonBusiness {
	
	public QuizBean() {
	
    }

}
