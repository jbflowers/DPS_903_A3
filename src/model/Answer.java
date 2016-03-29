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

    @ManyToOne
    private SubQuestion subQuestion;

    public Answer(){
        super();
    }
}