package com.nighthawk.spring_portfolio.mvc.students;

import java.util.ArrayList;

import javax.persistence.Column;

public class UpdatedStudentData extends Student {
    // stores current email as reference
    @Column(nullable = false, unique = true, length = 45)
    private String currentEmail;

    // constructor to instantiate updated data
    public UpdatedStudentData(Long id, String name, String passwordHash, ArrayList<String> event, int graduatingYear,
            String email, String phoneNumber, String currentEmail) {
        super(id, name, passwordHash, event, graduatingYear, email, phoneNumber);
        this.currentEmail = currentEmail;
    }

    // defines empty constructor from superclass
    public UpdatedStudentData() {
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
