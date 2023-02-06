package com.nighthawk.spring_portfolio.mvc.account;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import static javax.persistence.FetchType.EAGER;

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
public class Account {
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
    @Size(min = 2, max = 50, message = "Account Name (2 to 50 chars)")
    private String name;

    // To be implemented
    @ManyToMany(fetch = EAGER)
    private Collection<AccountRole> roles = new ArrayList<>();

    /*
     * @NonNull
     * private Note note = new Note("test", this);
     * 
     * public String getNote(){
     * return note.getText();
     * }
     * 
     * public void setNote(String note_input){
     * note.setText(note_input);;
     * }
     * 
     */

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Account Purpose (2 to 50 chars)")
    private String purpose;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 30, message = "Account President (2 to 30 chars)")
    private String president;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Staff Advisor (2 to 50 chars)")
    private String advisor;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 2, max = 50, message = "Meeting Time and Location (2 to 50 chars)")
    private String meeting;

    @Size(min = 2, max = 50, message = "Additional Info (2 to 50 chars)")
    private String info;

    // @NonNull: Places this in @RequiredArgsConstructor
    @NonNull
    @Size(min = 1, max = 1, message = "Official Account? (Y or N)")
    private String official;

    // Initializer used when setting database from an API
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Account[] init() {

        // basics of class construction
        Account gics = new Account();
        gics.setName("Girls in Computer Science");
        gics.setEmail("gics.dnhs@gmail.com");
        gics.setPassword("cs123%!");
        // p1.setNote("hi this is aadya");

        // adding Note to notes collection

        Account dnas = new Account();
        dnas.setName("Del Norte Arduino Society");
        dnas.setEmail("arduinoAccount.dnhs@gmail.com");
        dnas.setPassword("arduino!");
        // p2.setNote("hi this is prashant");

        Account nhs = new Account();
        nhs.setName("National Honors Society");
        nhs.setEmail("nhs@gmail.com");
        nhs.setPassword("nhs123!");
        // p3.setNote("hi this is sirish");

        Account optix = new Account();
        optix.setName("Del Norte Optix");
        optix.setEmail("optix@gmail.com");
        optix.setPassword("1234!");
        // p4.setNote("hi this is avan");

        Account gidas = new Account();
        gidas.setName("Genes in Diseases");
        gidas.setEmail("gidas@gmail.com");
        gidas.setPassword("54321!");
        // p5.setNote("hi this is efhdjwjkf");

        Account ncs = new Account();
        ncs.setName("Nighthawk Coding Society");
        ncs.setEmail("tedison@example.com");
        ncs.setPassword("123toby");

        // Array definition and data initialization
        Account Accounts[] = { gics, dnas, nhs, optix, gidas, ncs };
        return (Accounts);
    }

}