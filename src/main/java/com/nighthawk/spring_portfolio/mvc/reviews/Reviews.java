package com.nighthawk.spring_portfolio.mvc.reviews;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reviewername;

    @Column
    private int age;

    @Column
    private String reviewtext;

    @Column
    private String contact;

    private Reviews() {

    }

    protected Reviews(Long id, String reviewername, int age, String reviewtext, String contact) {
        if (reviewername == null)
            throw new NullPointerException("reviewername");
        this.age = age;
        this.reviewtext = reviewtext;
        System.out.println(reviewername);
        this.reviewername = reviewername;
        this.contact = contact;
    }

    public String getReviewername() {
        return this.reviewername;
    }

    public int getAge() {
        return this.age;
    }

    public String getReviewtext() {
        return this.reviewtext;
    }

    public String getContact() {
        return this.contact;
    }

    public void setReviewername(String reviewername) {
        this.reviewername = reviewername;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
