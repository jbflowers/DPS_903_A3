package model;

import javax.persistence.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class Answer {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;
	private String text;
	private Boolean isCorrect;

	@ManyToOne
    private Question question;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
    public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
    public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
	public Answer copy(Question question){
		Answer a = this;
		Answer b = new Answer();
		
		b.setIsCorrect(a.getIsCorrect());
		b.setQuestion(question);
		b.setText(a.getText());
		
		return b;
	}
	
    public Answer(){
        super();
    }
}