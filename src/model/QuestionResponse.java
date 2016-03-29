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

    private String type;

    private int attempt;

    public QuestionResponse(){
        super();
    }
}
