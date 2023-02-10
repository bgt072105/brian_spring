package com.nighthawk.spring_portfolio.mvc.tutors;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String teacher;

    @Column
    private int Unit;

    @Column
    private String Topic;

    @Column
    private String Tags;

    private Tutor() {

    }

    protected Tutor(Long id, String teacher, int Unit, String Topic, String Tags) {
        if (teacher == null)
            throw new NullPointerException("teacher");
        this.Unit = Unit;
        this.Topic = Topic;
        System.out.println(teacher);
        this.teacher = teacher;
        this.Tags = Tags;
    }

    public String getTeacher() {
        return this.teacher;
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

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setUnit(int Unit) {
        this.Unit = Unit;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }
}
