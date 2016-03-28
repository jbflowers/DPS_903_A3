package controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class QuizQuestionBean
 */
@Stateless
@LocalBean
public class QuizQuestionBean implements QuizQuestionRemote, QuizQuestionLocal {

    /**
     * Default constructor. 
     */
    public QuizQuestionBean() {
        // TODO Auto-generated constructor stub
    }

}
