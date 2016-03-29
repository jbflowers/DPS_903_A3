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

    @ManyToOne
    private Quiz quiz;

    // drop down case with inner questions
    @OneToMany
    private List<SubQuestion> subQuestions = new ArrayList<SubQuestion>();

    @OneToMany
    private List<Answer> Answers = new ArrayList<Answer>();

    public Question(){
        super();
    }
}