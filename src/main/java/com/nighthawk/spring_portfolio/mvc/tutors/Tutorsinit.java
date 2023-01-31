package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class Tutorsinit {

    // Inject repositories
    @Autowired
    TutorsJpaRepository repository;

    @Bean
    CommandLineRunner runTutors() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] tutorArray = {
                    "Hetvi",
                    "Iris",
            };

            // make sure Joke database is populated with starting jokes
            for (String tutor : tutorArray) {
                List<Tutors> test = repository.findByTutorsIgnoreCase(tutor); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tutors(null, 1, tutor, "1.1 Newton's Laws",
                            "physics, newton's laws, force, mass, acceleration")); // JPA save
            }

        };
    }
}