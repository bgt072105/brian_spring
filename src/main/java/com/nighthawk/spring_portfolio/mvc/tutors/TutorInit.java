package com.nighthawk.spring_portfolio.mvc.tutors;

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

            // starting jokes
            final String[] tutorsArray = {
                    "Previous tutor", "16", "hetvit27@gmail.com", "good tutor!",
            };

            // make sure Joke database is populated with starting jokes
            for (String experiences : tutorsArray) {
                List<Tutor> test = repository.findByExperienceIgnoreCase(experiences); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tutor(null, experiences, 1, "hetvit27@gmail.com",
                            "Good tutor! Very detailed")); // JPA save
            }

        };
    }
}
