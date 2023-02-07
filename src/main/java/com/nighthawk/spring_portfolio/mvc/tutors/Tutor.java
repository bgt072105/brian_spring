package com.nighthawk.spring_portfolio.mvc.tutors;

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
    private String name;

    @Column
    private int Age;

    @Column
    private String Experience;

    @Column
    private String Location;

    private Tutor() {

    }

    protected Tutor(Long id, String name, int Age, String Experience,
            String Location) {
        if (name == null)
            throw new NullPointerException("name");
        this.Age = Age;
        this.Experience = Experience;
        System.out.println(name);
        this.name = name;
        this.Location = Location;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.Age;
    }

    public String getExperience() {
        return this.Experience;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setExperience(String Experience) {
        this.Experience = Experience;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
}
