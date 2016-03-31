package utils;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Answer;
import model.Question;
import model.QuestionResponse;
import model.Quiz;
import model.QuizResponse;
import model.User;

public class DBManager {
    
	private final String PERSISTENCE_UNIT_NAME = "Models";
    private EntityManagerFactory factory;
	private EntityManager em;
	
	public DBManager(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	em.close();
		    	factory.close();
		    }
		});
	}
    
    public List<Quiz> getQuizzes(){
    	
    	List<Quiz> quizzes = (List<Quiz>) em.createQuery("SELECT e FROM Quiz e").getResultList();
    	
    	return quizzes;
    }
    
    public int commitQuiz(Quiz quiz){
    	for(Question question : quiz.getQuestions()){
    		commitQuestion(question);
    	}
    	
    	em.getTransaction().begin();
    	em.persist(quiz);
    	em.getTransaction().commit();
    	
    	return quiz.getId();
    }
    
    public Quiz getQuizById(int id){
    	
    	Quiz quiz = (Quiz) em.find(Quiz.class, id);
    	
    	return quiz;
    }
   
    public List<QuizResponse> getQuizResponses(){
    	
    	List<QuizResponse> quizResponses = (List<QuizResponse>) em.createQuery("SELECT e FROM QuizResponse e").getResultList();
    	
    	return quizResponses;
    }
    
    public int commitQuizResponse(QuizResponse quizResponse){
    	
    	em.getTransaction().begin();
    	em.persist(quizResponse);
    	em.getTransaction().commit();
    	
    	return quizResponse.getId();
    }
    
    public QuizResponse getQuizResponseById(int id){
    	
    	QuizResponse quizResponse = (QuizResponse) em.find(QuizResponse.class, id);
    	
    	return quizResponse;
    }
    
    public List<Question> getQuestions(){
    	em.getTransaction().begin();
    	List<Question> questions = (List<Question>) em.createQuery("SELECT e FROM Question e ORDER BY e.id").getResultList();
    	em.getTransaction().commit();
    	return questions;
    }
    
    public List<Question> getEasyQuestions(){
    	
    	List<Question> questions = (List<Question>) em.createQuery("SELECT e FROM Question e WHERE e.difficulty='easy'").getResultList();
    	
    	return questions;
    }
    
    public List<Question> getMediumQuestions(){
    	
    	List<Question> questions = (List<Question>) em.createQuery("SELECT e FROM Question e WHERE e.difficulty='medium'").getResultList();
    	
    	return questions;
    }
    
    public List<Question> getHardQuestions(){
    	
    	List<Question> questions = (List<Question>) em.createQuery("SELECT e FROM Question e WHERE e.difficulty='hard'").getResultList();
    	
    	return questions;
    }
    
    public Question getRandomEasyQuestion(){
    	List<Question> questions = getEasyQuestions();
    	Random rand = new Random();
    	int randNum = rand.nextInt((questions.size()-1) + 1);
    	return questions.get(randNum);
    }
    
    public Question getRandomMediumQuestion(){
    	List<Question> questions = getMediumQuestions();
    	Random rand = new Random();
    	int randNum = rand.nextInt((questions.size()-1) + 1);
    	return questions.get(randNum);
    }
    
    public Question getRandomHardQuestion(){
    	List<Question> questions = getHardQuestions();
    	Random rand = new Random();
    	int randNum = rand.nextInt((questions.size()-1) + 1);
    	return questions.get(randNum);
    }

	public void updateQuestion(Question question){
		em.getTransaction().begin();

		for(Answer answer: question.getAnswers()){
			em.persist(answer);
		}
		//em.merge(question);

		em.getTransaction().commit();
	}
    
    public int commitQuestion(Question question){
    	
    	em.getTransaction().begin();
    	for(Answer answer: question.getAnswers()){
    		em.persist(answer);
    	}
    	em.persist(question);

    	em.getTransaction().commit();
    	
    	return question.getId();
    }
    
    public Question getQuestionById(int id){
    	
    	Question question = (Question) em.find(Question.class, id);
    	
    	return question;
    }
    
    public List<QuestionResponse> getQuestionResponses(){
    	
    	List<QuestionResponse> questionResponses = (List<QuestionResponse>) em.createQuery("SELECT e FROM QuestionResponse e").getResultList();
    	
    	return questionResponses;
    }
    
    public int commitQuestionResponse(QuestionResponse questionResponse){
    	
    	em.getTransaction().begin();
    	em.persist(questionResponse);
    	em.getTransaction().commit();
    	
    	return questionResponse.getId();
    }
    
    public QuestionResponse getQuestionResponseById(int id){
    	
    	QuestionResponse questionResponse = (QuestionResponse) em.find(QuestionResponse.class, id);
    	
    	return questionResponse;
    }
    
    public List<Answer> getAnswers(){
    	
    	List<Answer> answers = (List<Answer>) em.createQuery("SELECT e FROM Answer e").getResultList();
    	
    	return answers;
    }
    
    public int commitAnswer(Answer answer){
    	
    	em.getTransaction().begin();
    	em.persist(answer);
    	em.getTransaction().commit();
    	
    	return answer.getId();
    }
    
    public Answer getAnswerById(int id){
    	
    	Answer answer = (Answer) em.find(Answer.class, id);
    	
    	return answer;
    }
    
    public List<User> getUsers(){
    	
    	List<User> users = (List<User>) em.createQuery("SELECT e FROM User e").getResultList();
    	
    	return users;
    }
    
    public String commitUser(User user){
    	
    	for(QuizResponse qr : user.getQuizResponses()){
    		commitQuizResponse(qr);
    	}
    	
    	em.getTransaction().begin();
    	em.persist(user);
    	em.getTransaction().commit();
    	
    	return user.getEmail();
    }
    
    public User getUserById(int id){
    	
    	User user = (User) em.find(User.class, id);
    	
    	return user;
    }
    
    public List<QuestionResponse> getQuestionResponsesQuestion(Question question){
    	em.getTransaction().begin();
    	
    	String query = "SELECT e FROM QuestionResponse e WHERE e.questionId=" + question.getId() ;
    	List<QuestionResponse> questionResponses = em.createQuery(query).getResultList();
    	
    	em.getTransaction().commit();
    	return questionResponses;
    }
    
    public User getUserByEmail(String email){
    	User user = (User) em.find(User.class, email);
    	return user;
    }

    public List<QuizResponse> getQuizResponsesForUser(User user){
    	String query = "SELECT e FROM QuizResponse e WHERE e.userId='" + user.getEmail() + "'";
    	List<QuizResponse> quizResponses = em.createQuery(query).getResultList();
    	return quizResponses;
    }
    
	public boolean userLogIn(String email, String salt){
		List<User> users = (List<User>) em.createNamedQuery("log in")
				.setParameter("pass", salt)
				.setParameter("email", email)
		        .setMaxResults(1)
				.getResultList();
		if (users == null || users.isEmpty()) { return false; }
		return true;
	}

	public void removeQuestionById(int id){
		Question question = getQuestionById(id);
		if(question != null){
			em.getTransaction().begin();

			em.remove(question);

			em.getTransaction().commit();
		}

	}

	public void removeAnswerById(int id){
		Answer answer = getAnswerById(id);

		if(answer != null){
			em.getTransaction().begin();

			em.remove(answer);

			em.getTransaction().commit();
		}

	}
	
    public void close(){
    	em.close();
    }
    
    public static void main(String[] args) {
    	
    	DBManager m = new DBManager();
    	
    	try {
    		List<Question> questions = m.getQuestions();
    		for(Question question : questions){
    			if (!question.isUsedInQuiz()){
    				System.out.println("Question text: " + question.getText());
    			}
    		}
    	}
    	finally{
    		//m.close();
    	}
    	
    }
    
}
