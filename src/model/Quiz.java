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
    
	@OneToMany
    private List<Message> messages = new ArrayList<Message>();

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

    public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
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

	public Quiz(){ super(); }
}
