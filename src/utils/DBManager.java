package utils;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Answer;
import model.Question;
import model.QuestionResponse;
import model.Quiz;
import model.QuizResponse;
import model.SubQuestion;
import model.User;
import model.Message;

public class DBManager {
    
	private final String PERSISTENCE_UNIT_NAME = "Models";
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;
    private EntityManager em = factory.createEntityManager();
    
    public DBManager(){
    	em.getTransaction().begin();
    }
    
    public ArrayList<Quiz> getQuizzes(){
    	ArrayList<Quiz> quizzes = (ArrayList<Quiz>) em.createQuery("SELECT e FROM Quiz e").getResultList();
    	return quizzes;
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
   
    public ArrayList<QuizResponse> getQuizResponses(){
    	ArrayList<QuizResponse> quizResponses = (ArrayList<QuizResponse>) em.createQuery("SELECT e FROM QuizResponse e").getResultList();
    	return quizResponses;
    }
    
    public int commitQuizResponse(QuizResponse quizResponse){
    	em.persist(quizResponse);
    	em.getTransaction().commit();
    	return quizResponse.getId();
    }
    
    public QuizResponse getQuizResponseById(int id){
    	QuizResponse quizResponse = (QuizResponse) em.find(QuizResponse.class, id);
    	return quizResponse;
    }
    
    public ArrayList<Question> getQuestions(){
    	ArrayList<Question> questions = (ArrayList<Question>) em.createQuery("SELECT e FROM Question e").getResultList();
    	return questions;
    }
    
    public int commitQuestion(Question question){
    	em.persist(question);
    	em.getTransaction().commit();
    	return question.getId();
    }
    
    public Question getQuestionById(int id){
    	Question question = (Question) em.find(Question.class, id);
    	return question;
    }
    
    public ArrayList<QuestionResponse> getQuestionResponses(){
    	ArrayList<QuestionResponse> questionResponses = (ArrayList<QuestionResponse>) em.createQuery("SELECT e FROM QuestionResponse e").getResultList();
    	return questionResponses;
    }
    
    public int commitQuestionResponse(QuestionResponse questionResponse){
    	em.persist(questionResponse);
    	em.getTransaction().commit();
    	return questionResponse.getId();
    }
    
    public QuestionResponse getQuestionResponseById(int id){
    	QuestionResponse questionResponse = (QuestionResponse) em.find(QuestionResponse.class, id);
    	return questionResponse;
    }
    
    public ArrayList<SubQuestion> getSubQuestions(){
    	ArrayList<SubQuestion> subQuestions = (ArrayList<SubQuestion>) em.createQuery("SELECT e FROM SubQuestion e").getResultList();
    	return subQuestions;
    }
    
    public int commitSubQuestion(SubQuestion subQuestion){
    	em.persist(subQuestion);
    	em.getTransaction().commit();
    	return subQuestion.getId();
    }
    
    public SubQuestion getSubQuestionById(int id){
    	SubQuestion subQuestion = (SubQuestion) em.find(SubQuestion.class, id);
    	return subQuestion;
    }
    
    public ArrayList<Answer> getAnswers(){
    	ArrayList<Answer> answers = (ArrayList<Answer>) em.createQuery("SELECT e FROM Answer e").getResultList();
    	return answers;
    }
    
    public int commitAnswer(Answer answer){
    	em.persist(answer);
    	em.getTransaction().commit();
    	return answer.getId();
    }
    
    public Answer getAnswerById(int id){
    	Answer answer = (Answer) em.find(Answer.class, id);
    	return answer;
    }
    
    public ArrayList<Message> getMessages(){
    	ArrayList<Message> messages = (ArrayList<Message>) em.createQuery("SELECT e FROM Message e").getResultList();
    	return messages;
    }
    
    public int commitMessage(Message message){
    	em.persist(message);
    	em.getTransaction().commit();
    	return message.getId();
    }
    
    public Message getMessageById(int id){
    	Message message = (Message) em.find(Message.class, id);
    	return message;
    }
    
    public ArrayList<User> getUsers(){
    	ArrayList<User> users = (ArrayList<User>) em.createQuery("SELECT e FROM User e").getResultList();
    	return users;
    }
    
    public String commitUser(User user){
    	em.persist(user);
    	em.getTransaction().commit();
    	return user.getEmail();
    }
    
    public User getUserById(int id){
    	User user = (User) em.find(User.class, id);
    	return user;
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
