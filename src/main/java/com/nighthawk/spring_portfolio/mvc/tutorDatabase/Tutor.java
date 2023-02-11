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
    private String name;

    @Column
    private int Unit;

    @Column
    private String Location;

    @Column
    private String Email;

    private Tutor() {

    }

    protected Tutor(Long id, String name, int Unit, String Location,
            String Email) {
        if (name == null)
            throw new NullPointerException(" name");
        this.Unit = Unit;
        this.Location = Location;
        System.out.println(name);
        this.name = name;
        this.Email = Email;
    }

    public String getName() {
        return this.name;
    }

    public int getUnit() {
        return this.Unit;
    }

    public String getLocation() {
        return this.Location;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(int Unit) {
        this.Unit = Unit;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
