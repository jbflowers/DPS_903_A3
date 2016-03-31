package model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class QuizResponse {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;
    
    private int mark;

    @OneToOne
    private Quiz quiz;

    @ManyToOne
    private User user;

    @OneToMany
    private List<QuestionResponse> questionResponses = new ArrayList<QuestionResponse>();

	long timestamp = System.currentTimeMillis();

    String userId;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.userId = user.getEmail();
		this.user = user;
	}

	public List<QuestionResponse> getQuestionResponses() {
		return questionResponses;
	}

	public void setQuestionResponses(List<QuestionResponse> questionResponses) {
		this.questionResponses = questionResponses;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

    public QuizResponse(){
        super();
    }
}
