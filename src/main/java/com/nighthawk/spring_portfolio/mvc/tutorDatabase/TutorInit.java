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

            // starting jokes
            final String[] questionsArray = {
                    "How to calculate the force of gravity?", "1", "1.1 newton's laws", "irisyang@gmail.com",
                    "How is potential energy converted into kinetic energy?", "3", "3.2 potential energy", "physics@student.com"
            };

            // make sure Joke database is populated with starting jokes

            for (String questions : questionsArray) {
                List<Tutor> test = repository.findByQuestionIgnoreCase(questions); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Tutor(null, questions, 1, "1.1 newton's laws",
                            "irisyang@gmail.com")); // JPA save
            }

        };
    }
}
