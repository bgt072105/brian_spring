package com.nighthawk.spring_portfolio.mvc.tutorDatabase;

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

            // clear db
            repository.deleteAll();

            // starting jokes
            final String[] questionsArray = {
                    "Hetvi Trivedi",
                    "Ellen Xu",
                    "Brian Tang",
                    "Iris Yang",
                    "Bailey Say",
                    "Mr. Liao",
                    "Kian Pakoshi",
            };

            // make sure Joke database is populated with starting jokes

            for (String names : questionsArray) {
                List<Tutor> test = repository.findByNameIgnoreCase(names); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tutor(null, names, 5, "San Diego",
                            "kian@gmail.com")); // JPA save
            }

        };
    }
}
