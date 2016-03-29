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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
    public int getMinMark() {
		return minMark;
	}

	public void setMinMark(int minMark) {
		this.minMark = minMark;
	}
	
    public int getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(int maxMark) {
		this.maxMark = maxMark;
	}
	
    public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
    public Message(){
        super();
    }
}
