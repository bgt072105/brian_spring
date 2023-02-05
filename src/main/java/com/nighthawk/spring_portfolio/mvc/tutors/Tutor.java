package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
@Table(name = "tutors")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Stores name respectively of student
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    // Stores password of each student/user
    @Column(nullable = false, length = 64)
    private String passwordHash;

    // Stores list of events that each student has
    private ArrayList<String> experience = new ArrayList<String>();

    // Stores graduating year
    private int age;

    // Stores email
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    // Stores phone number
    private String meetingpreference;

    // delombok: class definition
    public Tutor(Long id, String name, String passwordHash, ArrayList<String> experience,
            int age, String email,
            String meetingpreference) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        if (this.experience != null)
            this.experience = experience;
        this.age = age;
        this.email = email;
        this.meetingpreference = meetingpreference;
    }

    // no args
    public Tutor() {
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getExperience() {
        return experience;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean doesExperienceExist(String experience) {
        if (experience.contains(experience))
            return true;
        else
            return false;
    }

    public void setExperience(ArrayList<String> experience) {
        this.experience = experience;
    }

    public void removeExperience(int index) {
        this.experience.remove(index);
    }

    public void addExperience(String experience) {
        this.experience.add(experience);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMeetingpreference() {
        return meetingpreference;
    }

    public void setMeetingpreference(String meetingpreference) {
        this.meetingpreference = meetingpreference;
    }

    // data: tostring (not in use)
    @Override
    public String toString() {
        return "Tutor [id=" + id + ", name=" + name + ",  experience=" + experience
                + ", age=" + age + ", email=" + email + ", meetingpreference=" + meetingpreference + "]";
    }

    // data: check if equal for anything (not in use)
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tutor other = (Tutor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (meetingpreference == null) {
            if (other.meetingpreference != null)
                return false;
        } else if (!meetingpreference.equals(other.meetingpreference))
            return false;
        if (experience == null) {
            if (other.experience != null)
                return false;
        } else if (!experience.equals(other.experience))
            return false;
        if (age != other.age)
            return false;
        return true;
    }

    // creates hash (not in use)
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((experience == null) ? 0 : experience.hashCode());
        result = prime * result + age;
        return result;
    }
}