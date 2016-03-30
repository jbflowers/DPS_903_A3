package utils;

import model.Answer;
import model.Question;
import model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class DummyDataCreator {
	private static DBManager dbm = new DBManager();
	
	public static void generateQuiz(){
		// Make your quiz
		Quiz quiz = new Quiz();
		List<Question> questions = generateQuizQuestions();
		quiz.setQuestions(questions);
		quiz.setAllowHints(true);
		
		// Store it
		dbm.commitQuiz(quiz);
		System.out.println("made it here!");
	}
	
	
	// Makes a list of questions composed of 3 easy, 2 medium, and 1 hard
	public static List<Question> generateQuizQuestions(){
		List<Question> questions = new ArrayList<Question>();

		// Make three easy questions
		while(questions.size() != 3){
			Question easyQuestion = dbm.getRandomEasyQuestion();
			if (!questions.contains(easyQuestion)){
				questions.add(easyQuestion);
			}
		}
		
		// Make two unique medium questions
		while(questions.size() != 5){
			Question mediumQuestion = dbm.getRandomMediumQuestion();
			if (!questions.contains(mediumQuestion)){
				questions.add(mediumQuestion);
			}
		}
		
		// Make one hard question
		questions.add(dbm.getRandomHardQuestion());
		
		return questions;
	}
	
	public static void generateQuestions(){
		// Make first question
		Question q1 = new Question();
		
		q1.setType("mc");
		q1.setText("From this list, who is the most important?");
		q1.setDifficulty("easy");
		q1.setAttemptsBeforeHint(3);
		q1.setHint("He has won the most grand slams of the modern era");
		
		Answer q1a1 = new Answer();
		q1a1.setIsCorrect(true);
		q1a1.setText("Federer");
		
		Answer q1a2 = new Answer();
		q1a2.setIsCorrect(false);
		q1a2.setText("Ghandi");
		
		Answer q1a3 = new Answer();
		q1a3.setIsCorrect(false);
		q1a3.setText("Mother Teresa");
		
		ArrayList<Answer> q1Answers = new ArrayList<Answer>();
		q1Answers.add(q1a1); q1Answers.add(q1a2); q1Answers.add(q1a3);
		
		q1.setAnswers(q1Answers);	
		dbm.commitQuestion(q1);
		
		// Make second question
		Question q2 = new Question();
		
		q2.setType("text");
		q2.setText("Write out 'Justin' exactly.");
		q2.setDifficulty("easy");
		q2.setAttemptsBeforeHint(3);
		q2.setHint("Dude, it's not even a question.");
		
		Answer q2a1 = new Answer();
		q2a1.setIsCorrect(true);
		q2a1.setText("Justin");
		
		ArrayList<Answer> q2Answers = new ArrayList<Answer>();
		q2Answers.add(q2a1);
		
		q2.setAnswers(q2Answers);	
		dbm.commitQuestion(q2);
		
		// Make third question
		Question q3 = new Question();
		
		q3.setType("check");
		q3.setText("From this list, what is in fact a type of cat and not a flammable material?");
		q3.setDifficulty("easy");
		q3.setAttemptsBeforeHint(3);
		q3.setHint("'Gun poweder' is in fact flammable");
		
		Answer q3a1 = new Answer();
		q3a1.setIsCorrect(false);
		q3a1.setText("Nylon");
		
		Answer q3a2 = new Answer();
		q3a2.setIsCorrect(false);
		q3a2.setText("Gun powder");
		
		Answer q3a3 = new Answer();
		q3a3.setIsCorrect(true);
		q3a3.setText("Fuzzball");
		
		ArrayList<Answer> q3Answers = new ArrayList<Answer>();
		q3Answers.add(q3a1); q3Answers.add(q3a2); q3Answers.add(q3a3);
		
		q3.setAnswers(q3Answers);	
		dbm.commitQuestion(q3);
		
		// Make fourth question
		Question q4 = new Question();
		
		q4.setType("check");
		q4.setText("From this list, what is tasty?");
		q4.setDifficulty("medium");
		q4.setAttemptsBeforeHint(3);
		q4.setHint("Nobody likes sardines");
		
		Answer q4a1 = new Answer();
		q4a1.setIsCorrect(false);
		q4a1.setText("Sardines");
		
		Answer q4a2 = new Answer();
		q4a2.setIsCorrect(true);
		q4a2.setText("Tacos");
		
		Answer q4a3 = new Answer();
		q4a3.setIsCorrect(true);
		q4a3.setText("Sushi");
		
		ArrayList<Answer> q4Answers = new ArrayList<Answer>();
		q4Answers.add(q4a1); q4Answers.add(q4a2); q4Answers.add(q4a3);
		
		q4.setAnswers(q4Answers);	
		dbm.commitQuestion(q4);
		
		// Make fifth question
		Question q5 = new Question();
		
		q5.setType("text");
		q5.setText("What is the most used text editor for Linux?");
		q5.setDifficulty("medium");
		q5.setAttemptsBeforeHint(3);
		q5.setHint("It stands for VIsual iMproved");
		
		Answer q5a1 = new Answer();
		q5a1.setIsCorrect(true);
		q5a1.setText("VIM");
		
		ArrayList<Answer> q5Answers = new ArrayList<Answer>();
		q5Answers.add(q5a1);
		
		q5.setAnswers(q5Answers);	
		dbm.commitQuestion(q5);
		
		// Make sixth question
		Question q6 = new Question();
		
		q6.setType("mc");
		q6.setText("Do or do not, there is no ___.");
		q6.setDifficulty("hard");
		q6.setAttemptsBeforeHint(3);
		q6.setHint("Attempt?");
		
		Answer q6a1 = new Answer();
		q6a1.setIsCorrect(false);
		q6a1.setText("savior");
		
		Answer q6a2 = new Answer();
		q6a2.setIsCorrect(false);
		q6a2.setText("more bananas");
		
		Answer q6a3 = new Answer();
		q6a3.setIsCorrect(true);
		q6a3.setText("try");
		
		Answer q6a4 = new Answer();
		q6a4.setIsCorrect(false);
		q6a4.setText("god");
		
		ArrayList<Answer> q6Answers = new ArrayList<Answer>();
		q6Answers.add(q6a1); q6Answers.add(q6a2); q6Answers.add(q6a3); q6Answers.add(q6a4);
		
		q6.setAnswers(q6Answers);	
		dbm.commitQuestion(q6);
	}
	
	public static void main(String args[]){
		/*
		
		
		List<Question> easyQuestions = dbm.getEasyQuestions();
		for(Question question : easyQuestions){
			System.out.println(question.getText());
		}
		*/
		generateQuestions();
		//generateQuiz();
	}
}
