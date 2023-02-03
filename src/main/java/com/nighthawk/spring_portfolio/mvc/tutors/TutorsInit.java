package com.nighthawk.spring_portfolio.mvc.tutors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class TutorsInit {

    // Inject repositories
    @Autowired
    TutorsJpaRepository repository;

    @Bean
    CommandLineRunner run1() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] tutorsArray = {

                    "Hetvi",
                    "Iris",
                    "Brian",
            };

            // make sure Joke database is populated with starting jokes
            for (String tutor : tutorsArray) {
                List<Tutors> test = repository.findByTutorIgnoreCase(tutor); // JPA lookup

                // UNCOMMENT TO CLEAR DATABASE
                // if (test.size() > 0) {
                // repository.deleteAll();
                // }

                if (test.size() == 0)
                    repository.save(
                            new Tutors(null, tutor, "san diego", "qualified tutor", "User8234: excellent", "Zoom!")); // JPA
                                                                                                                      // save
            }

        };
    }
}
