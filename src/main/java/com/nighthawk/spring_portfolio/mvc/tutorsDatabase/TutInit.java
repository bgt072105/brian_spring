package com.nighthawk.spring_portfolio.mvc.tutorsDatabase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class TutInit {

    // Inject repositories
    @Autowired
    TutJpaRepository repository;

    @Bean
    CommandLineRunner runTut() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validation
            // starting jokes
            final String[] namesArray = {
                    "Hetvi",

            };

            // make sure Joke database is populated with starting jokes

            for (String tutornames : namesArray) {
                List<Tut> test = repository.findByTutornameIgnoreCase(tutornames); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tut(null, tutornames, 17, "San Diego",
                            "hetvit27@gmail.com")); // JPA save
            }

        };
    }
}
