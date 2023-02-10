package com.nighthawk.spring_portfolio.mvc.tutorDatabase;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String question;

    @Column
    private int Unit;

    @Column
    private String Tags;

    @Column
    private String Email;

    private Tutor() {

    }

    protected Tutor(Long id, String question, int Unit, String Tags,
            String Email) {
        if (question == null)
            throw new NullPointerException("question");
        this.Unit = Unit;
        this.Tags = Tags;
        System.out.println(question);
        this.question = question;
        this.Email = Email;
    }

    public String getQuestion() {
        return this.question;
    }

    public int getUnit() {
        return this.Unit;
    }

    public String getTags() {
        return this.Tags;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUnit(int Unit) {
        this.Unit = Unit;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}