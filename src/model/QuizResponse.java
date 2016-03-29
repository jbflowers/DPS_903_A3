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

    public QuizResponse(){
        super();
    }
}
