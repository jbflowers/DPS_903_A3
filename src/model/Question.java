package model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class Question {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;
    
	private String type;
    private String difficulty;
    private String text;
    private int attemptsBeforeHint;
    private String hint;

    @ManyToOne
    private Quiz quiz;

    // drop down case with inner questions
    @OneToMany
    private List<SubQuestion> subQuestions = new ArrayList<SubQuestion>();

    @OneToMany
    private List<Answer> Answers = new ArrayList<Answer>();
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAttemptsBeforeHint() {
		return attemptsBeforeHint;
	}

	public void setAttemptsBeforeHint(int attemptsBeforeHint) {
		this.attemptsBeforeHint = attemptsBeforeHint;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<SubQuestion> getSubQuestions() {
		return subQuestions;
	}

	public void setSubQuestions(List<SubQuestion> subQuestions) {
		this.subQuestions = subQuestions;
	}

	public List<Answer> getAnswers() {
		return Answers;
	}

	public void setAnswers(List<Answer> answers) {
		Answers = answers;
	}



    public Question(){
        super();
    }
}