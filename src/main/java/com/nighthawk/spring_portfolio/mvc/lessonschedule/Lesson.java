package com.nighthawk.spring_portfolio.mvc.lessonschedule;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private int Grade;

    @Column
    private String Course;

    @Column
    private String Email;

    private Lesson() {

    }

    protected Lesson(Long id, String name, int Grade, String Course, String Email) {
        if (name == null)
            throw new NullPointerException("name");
        this.Grade = Grade;
        this.Course = Course;
        System.out.println(name);
        this.name = name;
        this.Email = Email;
    }

    public String getName() {
        return this.name;
    }

    public int getGrade() {
        return this.Grade;
    }

    public String getCourse() {
        return this.Course;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    /// Tester Method ToString
    public String toString() {
        return ("{ \"name\": " + this.name + ", " + "\"Course\": " + this.Course
                + ", " + "\"Email\": " + this.Email + "}");
    }

    public static void main(String[] args) {
        Lesson newLesson = new Lesson();
        newLesson.setName("Lesson");
        newLesson.setCourse("E&M");
        newLesson.setEmail("lesson@gmail.com");

        System.out.println(newLesson.toString());
    }
}
