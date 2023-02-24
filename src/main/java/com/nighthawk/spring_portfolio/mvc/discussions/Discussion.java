package com.nighthawk.spring_portfolio.mvc.discussions;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String question;

    @Column
    private int unit;

    @Column
    private String tags;

    @Column
    private String email;

    private Discussion() {

    }

    protected Discussion(Long id, String question, int unit, String tags,
            String email) {
        if (question == null)
            throw new NullPointerException("question");
        this.unit = unit;
        this.tags = tags;
        System.out.println(question);
        this.question = question;
        this.email = email;
    }

    public String getQuestion() {
        return this.question;
    }

    public int getUnit() {
        return this.unit;
    }

    public String getTags() {
        return this.tags;
    }

    public String getEmail() {
        return this.email;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /// Tester Method ToString
    public String toString() {
        return ("{ \"question\": " + this.question + ", " + "\"tags\": " + this.tags
                + ", " + "\"email\": " + this.email + "}");
    }

    public static void main(String[] args) {
        Discussion newQuestion = new Discussion();
        newQuestion.setQuestion("What is the formula for calculating kinetic energy?");
        newQuestion.setTags("physics");
        newQuestion.setEmail("shriya@gmail.com");

        System.out.println(newQuestion.toString());
    }

}
