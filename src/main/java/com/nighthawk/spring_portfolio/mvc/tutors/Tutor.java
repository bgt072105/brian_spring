package com.nighthawk.spring_portfolio.mvc.tutors;

import javax.persistence.*;

import org.springframework.data.repository.util.ReactiveWrapperConverters;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String experience;

    @Column
    private int Age;

    @Column
    private String Email;

    @Column
    private String Review;

    private Tutor() {

    }

    protected Tutor(Long id, String experience, int Age, String Email, String Review) {
        if (experience == null)
            throw new NullPointerException("experience");
        this.Age = Age;
        this.Email = Email;
        System.out.println(experience);
        this.experience = experience;
        this.Review = Review;
    }

    public String getExperience() {
        return this.experience;
    }

    public int getAge() {
        return this.Age;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getReview() {
        return this.Review;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void set(String Review) {
        this.Review = Review;
    }
}
