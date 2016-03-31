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
    private boolean usedInQuiz = false;

	@ManyToOne
    private Quiz quiz;

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

	public List<Answer> getAnswers() {
		return Answers;
	}

	public void setAnswers(List<Answer> answers) {
		Answers = answers;
	}

	public Question copy(){
		Question a = this;
		Question b = new Question();
		
		List<Answer> bAnswers = new ArrayList<Answer>();
		for(Answer answer : a.getAnswers()){
			bAnswers.add(answer.copy(b));
		}
		
		b.setAnswers(bAnswers);
		b.setAttemptsBeforeHint(a.getAttemptsBeforeHint());
		b.setDifficulty(a.getDifficulty());
		b.setHint(a.getHint());
		b.setText(a.getText());
		b.setType(a.getType());
		
		return b;
	}

	public boolean equals(Question b){
		
		if(!b.getText().equals(this.getText())){
			return false;
		}
		
		if(!b.getType().equals(this.getType())){
			return false;
		}
		
		return true;
	}
	
    public boolean isUsedInQuiz() {
		return usedInQuiz;
	}

	public void setUsedInQuiz(boolean usedInQuiz) {
		this.usedInQuiz = usedInQuiz;
	}
	
    public Question(){
        super();
    }
}