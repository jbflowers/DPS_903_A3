package model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class Quiz {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;
    private String name;
    private String description;
    private Boolean allowHints;
    private String status = "in-progress";
    private long timestamp = System.currentTimeMillis();

	@OneToOne
    private QuizResponse quizResponse;
	
	@OneToMany
    private List<Question> questions = new ArrayList<Question>();
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public Boolean getAllowHints() {
		return allowHints;
	}

	public void setAllowHints(Boolean allowHints) {
		this.allowHints = allowHints;
	}

    public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

    public QuizResponse getQuizResponse() {
		return quizResponse;
	}

	public void setQuizResponse(QuizResponse quizResponse) {
		this.quizResponse = quizResponse;
	}

	// "in-progress", "complete", or "incomplete"
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Quiz(){ super(); }
}
