package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.ArrayList;

import javax.persistence.Column;

public class UpdatedTutorData extends Tutor {
    // stores current email as reference
    @Column(nullable = false, unique = true, length = 45)
    private String currentEmail;

    // constructor to instantiate updated data
    public UpdatedTutorData(Long id, String name, String passwordHash, ArrayList<String> experience, int age,
            String email, String meetingpreference, String currentEmail) {
        super(id, name, passwordHash, experience, age, email, meetingpreference);
        this.currentEmail = currentEmail;
    }

    // defines empty constructor from superclass
    public UpdatedTutorData() {
        super();
    }

    // getter & setter for new email
    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }
}
