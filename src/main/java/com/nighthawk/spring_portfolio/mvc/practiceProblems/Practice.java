package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String Problem;
    
    @Column
    private int Unit;

    @Column
    private String Topic ;

    @Column
    private String Tags;

    private Practice() {   

    }

    protected Practice(Long id, String Problem, int Unit, String Topic, String Tags) {
        if (Problem == null) throw new NullPointerException("Problem");
        this.Unit = Unit;
        this.Topic = Topic;
        System.out.println(Problem);
        this.Problem = Problem;
        this.Tags = Tags;
    }

    public String getProblem() {
        return this.Problem;
    }

    public int getUnit() {
        return this.Unit;
    }

    public String getTopic() {
        return this.Topic;
    }

    public String getTags() {
        return this.Tags;
    }

    public void setProblem(String Problem) {
        this.Problem=Problem;
    }

    public void setUnit(int Unit) {
        this.Unit=Unit;
    }

    public void setTopic(String Topic) {
        this.Topic=Topic;
    }

    public void setTags(int Tags) {
        this.Tags=Tags;
    }
}
