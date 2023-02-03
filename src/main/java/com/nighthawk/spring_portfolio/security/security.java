package com.nighthawk.spring_portfolio.security;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class security {
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key to login and authentication
    @NotEmpty
    @Size(min = 5)
    @Column(unique = true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Student Name (2 to 50 chars)")
    private String name;

    /*
     * @NonNull
     * private Note note = new Note("test", this);
     * public String getNote(){
     * return note.getText();
     * }
     * public void setNote(String note_input){
     * note.setText(note_input);;
     * }
     */

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Desire for account (2 to 50 chars)")
    private String purpose;

    // Initializer used when setting database from an API
    public security(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static security[] init() {

        // basics of class construction
        security Iris = new security();
        Iris.setName("Iris Yang");
        Iris.setEmail("irisyang@gmail.com");
        Iris.setPassword("irisyang2023");

        security Brian = new security();
        Brian.setName("Brian Tang");
        Brian.setEmail("bgt@gmail.com");
        Brian.setPassword("bgt!");
        // p2.setNote("hi this is prashant");

        security Ellen = new security();
        Ellen.setName("Ellen Xu");
        Ellen.setEmail("ellen@gmail.com");
        Ellen.setPassword("ellenx1!");

        // Array definition and data initialization
        security students[] = { Iris, Brian, Ellen };
        return (students);
    }

}