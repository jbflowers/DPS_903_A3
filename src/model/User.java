package model;

import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
@NamedQuery(query = "Select e from User e where e.email = :email and e.password = :pass", name = "log in")
public class User {
    @Id
    // Mark that this field is the primary key
    private String email;

	@NotNull
	@Column(nullable = false, unique = false)
	private String name;

	@NotNull
	@Column(nullable = false, unique = false)
    private String password;

	@NotNull
	@Column(nullable = false, unique = false)
	private String role;

    @OneToMany
    private List<QuizResponse> quizResponses = new ArrayList<QuizResponse>();
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getRole() { return role; }

	public void setRole(String role) { this.role = role; }

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
