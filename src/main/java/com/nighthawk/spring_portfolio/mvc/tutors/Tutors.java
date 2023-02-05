package com.nighthawk.spring_portfolio.mvc.tutors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Tutors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Unique identifier

    @Column(unique = true)
    private String tutor; // The Joke
    private String location;
    private String experience;
    private String reviews;
    private String meetingpreference;

    public String getLocation() {
        return this.location;
    }

    public String getTutors() {
        return this.tutor;
    }

    public String getExperience() {
        return this.experience;
    }

    public String getReviews() {
        return this.reviews;
    }

    public String getMeetingpreference() {
        return this.meetingpreference;
    }

    public void setTutors(String tutor) {
        this.tutor = tutor;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public void setMeetingpreference(String meetingpreference) {
        this.meetingpreference = meetingpreference;
    }
}

// public String getTutors() {
// return this.tutor;
// }

// public String getlocation() {
// return this.location;
// }

// public String getexperience() {
// return this.experience;
// }

// public String getreviews() {
// return this.reviews;
// }

// public String getmeetingpreference() {
// return this.meetingpreference;
// }
//
// public void setTutors(String tutor) {
// this.tutor = tutor;
// }

// public void setlocation(String location) {
// this.location = location;
// }

// public void setexperience(String experience) {
// this.experience = experience;
// }

// public void setreviews(String reviews) {
// this.reviews = reviews;
// }
