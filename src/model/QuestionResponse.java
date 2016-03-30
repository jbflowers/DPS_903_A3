package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class QuestionResponse {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;

    private int attempt;
    
    private int questionId;
    
    private boolean isCorrect = false;

	// Can be an id pointing to an answer or a text response depending on question type
    private String[] questionResponse;

	public int getId() {
		return id;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

    public String[] getQuestionResponse() {
		return questionResponse;
	}

	public void setQuestionResponse(String[] questionResponse) {
		this.questionResponse = questionResponse;
	}

    public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
    public QuestionResponse(){
        super();
    }
}
