package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class TutorInit {

    // Inject repositories
    @Autowired
    TutorJpaRepository repository;

    @Bean
    CommandLineRunner runTutor() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // 2d array for names and graduating year
            final String[][] peopleArray = {
                    { "Don Tran", "donqt15@gmail.com", "online" },
                    { "Krish Patil", "19", "n/a", "n/a" },
                    { "Bailey Say", "19", "n/a", "n/a" },
                    { "Rohan Gaikwad", "19", "n/a", "n/a" },
                    { "Andrew Meng", "19", "n/a", "n/a" },
                    { "Nicolas Mounier", "19", "n/a", "n/a" },
                    { "Nicholas Ramos", "19", "n/a", "n/a" },
                    { "Joe Jack Bill", "19", "n/a", "n/a" }
            };

            // make sure people array database is populated with starting values for members
            for (int i = 0; i < peopleArray.length; i++) {
                String name = peopleArray[i][0];
                String email = peopleArray[i][1];
                String meetingpreference = peopleArray[i][2];
                List<Tutor> test = repository.findByNameIgnoreCase(name); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tutor(null, name, "", new ArrayList<String>(), email,
                            meetingpreference)); // JPA save
            }

        };
    }
}