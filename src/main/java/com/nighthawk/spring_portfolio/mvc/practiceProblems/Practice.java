package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String problem;
    
    @Column
    private int Unit;

    @Column
    private String Topic ;

    @Column
    private String Tags;

    private Practice() {   

    }

    protected Practice(Long id, String problem, int Unit, String Topic, String Tags) {
        if (problem == null) throw new NullPointerException("problem");
        this.Unit = Unit;
        this.Topic = Topic;
        System.out.println(problem);
        this.problem = problem;
        this.Tags = Tags;
    }

    public String getProblem() {
        return this.problem;
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

    public void setProblem(String problem) {
        this.problem=problem;
    }

    public void setUnit(int Unit) {
        this.Unit=Unit;
    }

    public void setTopic(String Topic) {
        this.Topic=Topic;
    }

    public void setTags(String Tags) {
        this.Tags=Tags;
    }
}
