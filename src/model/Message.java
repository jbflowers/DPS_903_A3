package model;

import javax.persistence.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class Message {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;
    private String text;
    private int minMark;
    private int maxMark;

    @ManyToOne
    private Quiz quiz;

    public Message(){
        super();
    }
}
