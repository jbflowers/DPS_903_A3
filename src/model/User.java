package model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class User {
    @Id
    // Mark that this field is the primary key
    private String email;
	private String name;
    private String password;

    @OneToMany
    private List<QuizResponse> quizResponses = new ArrayList<QuizResponse>();
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<QuizResponse> getQuizResponses() {
		return quizResponses;
	}

	public void setQuizResponses(List<QuizResponse> quizResponses) {
		this.quizResponses = quizResponses;
	}

    public User(){
        super();
    }
}
