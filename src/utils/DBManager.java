package utils;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Quiz;

public class DBManager {
    
	private final String PERSISTENCE_UNIT_NAME = "Models";
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;
    private EntityManager em = factory.createEntityManager();
    
    private DBManager(){
    	em.getTransaction().begin();
    }
    
    public int commitQuiz(Quiz quiz){
    	em.persist(quiz);
    	em.getTransaction().commit();
    	return quiz.getId();
    }
    
    public Quiz getQuizById(int id){
    	Quiz quiz = (Quiz) em.find(Quiz.class, id);
    	return quiz;
    }
    
    public void close(){
    	em.close();
    }
    
    public static void main(String[] args) {
    	
    	DBManager m = new DBManager();
    	
    	Quiz quiz = new Quiz();
    	
    	quiz.setName("Quiz 1");
    	quiz.setDescription("A dummy quiz");
    	quiz.setAllowHints(false);
    	
    	int id = m.commitQuiz(quiz);
    	
    	try {
    		Quiz result = m.getQuizById(id);
    		System.out.println("Quiz name is: " + result.getName());
    	}
    	finally{
    		m.close();
    	}
    	
    }
    
}
