package com.nighthawk.spring_portfolio.mvc.tutorsDatabase;

import java.util.ArrayList;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Tut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String tutorname;

    @Column
    private int age;

    @Column
    private String area;

    @Column
    private String contact;

    private Tut() {

    }

    protected Tut(Long id, String tutorname, int age, String area, String contact) {
        if (tutorname == null)
            throw new NullPointerException("tutorname");
        this.age = age;
        this.area = area;
        System.out.println(tutorname);
        this.tutorname = tutorname;
        this.contact = contact;
    }

    public String getTutorname() {
        return this.tutorname;
    }

    public int getAge() {
        return this.age;
    }

    public String getArea() {
        return this.area;
    }

    public String getContact() {
        return this.contact;
    }

    public void setTutorname(String tutorname) {
        this.tutorname = tutorname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
