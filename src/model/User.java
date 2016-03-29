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

    public User(){
        super();
    }
}
