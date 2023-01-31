package com.nighthawk.spring_portfolio.mvc.tutors;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence
        // domain object. Typically, an entity represents a table in a relational
        // database, and each entity instance corresponds to a row in that table.
public class Tutors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String tutor;

    @Column
    private int Location;

    @Column
    private String Experience;

    @Column
    private String PastReviews;

    @Column
    private String MeetingPreference;

    protected Tutors(Long id, int Location, String Experience, String PastReviews, String MeetingPreference) {
        if (tutor == null)
            throw new NullPointerException("tutor");
        this.Location = Location;
        this.Experience = Experience;
        this.PastReviews = PastReviews;
        this.MeetingPreference = MeetingPreference;
        System.out.println("tutor");

    }

    public String getTutor() {
        return this.tutor;
    }

    public int getLocation() {
        return this.Location;
    }

    public String getExperience() {
        return this.Experience;
    }

    public String getPastReviews() {
        return this.PastReviews;
    }

    public String getMeetingPreference() {
        return this.MeetingPreference;
    }

    public void setTutors(String tutor) {
        this.tutor = tutor;
    }

    public void setLocation(int i) {
        this.Location = i;
    }

    public void setExperience(String Experience) {
        this.Experience = Experience;
    }

    public void setPastReviews(String PastReviews) {
        this.PastReviews = PastReviews;
    }

    public void setMeetingPreference(String MeetingPreference) {
        this.MeetingPreference = MeetingPreference;
    }
}
