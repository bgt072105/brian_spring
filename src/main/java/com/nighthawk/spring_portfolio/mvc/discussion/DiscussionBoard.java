package com.nighthawk.spring_portfolio.mvc.discussion;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class DiscussionBoard {

    @Column(unique = true)
    private String Question;

    @Column
    private String Unit;

    @Column
    private String Tags;

    private DiscussionBoard() {

    }

    protected DiscussionBoard(String string, String Question, String Unit, String Tags) {
        if (Question == null)
            throw new NullPointerException("Question");
        this.Unit = Unit;
        System.out.println(Question);
        this.Question = Question;
        this.Tags = Tags;
    }

    public String getQuestion() {
        return this.Question;
    }

    public String getUnit() {
        return this.Unit;
    }

    public String getTags() {
        return this.Tags;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }
}
