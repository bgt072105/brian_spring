package com.nighthawk.spring_portfolio.mvc.discussion;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class DiscussionBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String question;
    
    @Column
    private String Unit;

    @Column
    private String Tags;

    private DiscussionBoard() {   

    }

    protected DiscussionBoard(Long id, String question, String Unit, String Tags) {
        if (question == null) throw new NullPointerException("question");
        this.Unit = Unit;
        System.out.println(question);
        this.question = question;
        this.Tags = Tags;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getUnit() {
        return this.Unit;
    }

    public String getTags() {
        return this.Tags;
    }

    public void setQuestion(String question) {
        this.question=question;
    }

    public void setUnit(String Unit) {
        this.Unit=Unit;
    }

    public void setTags(String Tags) {
        this.Tags=Tags;
    }
}
