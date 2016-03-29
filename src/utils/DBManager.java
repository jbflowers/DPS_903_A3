package utils;

import java.util.List;
import java.util.Random;
import java.util.List;

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
    	//em.getTransaction().begin();
    }
    
    public List<Quiz> getQuizzes(){
    	List<Quiz> quizzes = (List<Quiz>) em.createQuery("SELECT e FROM Quiz e").getResultList();
    	return quizzes;
    }
    
    public int commitQuiz(Quiz quiz){
    	for(Question question : quiz.getQuestions()){
    		commitQuestion(question);
    	}
    	for(Message message : quiz.getMessages()){
    		commitMessage(message);
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
    	List<Question> questions = (List<Question>) em.createQuery("SELECT e FROM Question e").getResultList();
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
    
    public List<SubQuestion> getSubQuestions(){
    	List<SubQuestion> subQuestions = (List<SubQuestion>) em.createQuery("SELECT e FROM SubQuestion e").getResultList();
    	return subQuestions;
    }
    
    public int commitSubQuestion(SubQuestion subQuestion){
    	em.getTransaction().begin();
    	em.persist(subQuestion);
    	em.getTransaction().commit();
    	return subQuestion.getId();
    }
    
    public SubQuestion getSubQuestionById(int id){
    	SubQuestion subQuestion = (SubQuestion) em.find(SubQuestion.class, id);
    	return subQuestion;
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
    
    public List<Message> getMessages(){
    	List<Message> messages = (List<Message>) em.createQuery("SELECT e FROM Message e").getResultList();
    	return messages;
    }
    
    public int commitMessage(Message message){
    	em.getTransaction().begin();
    	em.persist(message);
    	em.getTransaction().commit();
    	return message.getId();
    }
    
    public Message getMessageById(int id){
    	Message message = (Message) em.find(Message.class, id);
    	return message;
    }
    
    public List<User> getUsers(){
    	List<User> users = (List<User>) em.createQuery("SELECT e FROM User e").getResultList();
    	return users;
    }
    
    public String commitUser(User user){
    	em.getTransaction().begin();
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
