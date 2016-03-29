package model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by pavlick on 2016-02-11.
 */
@Entity
public class SubQuestion {
    @Id
    // Mark that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // The value of the primary key will be automatically generated.
    private int id;

    @ManyToOne
    private Question question;

    @OneToMany
    private List<Answer> answers = new ArrayList<Answer>();

    public SubQuestion(){
        super();
    }
}