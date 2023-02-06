
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
            final String[] namesArray = {
                    "Hetvi", "16", "Tutors in STEM subjects", "San Diego",
                    "Ellen", "16", "Tutors in AP STEM subjects", "San Diego"
            };

            // make sure Joke database is populated with starting jokes

            for (String names : namesArray) {
                List<Tutor> test = repository.findByNameIgnoreCase(names); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tutor(null, names, 16, "Tutors in STEM subjects",
                            "San Diego")); // JPA save
            }

        };
    }
}
