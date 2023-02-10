package com.nighthawk.spring_portfolio.mvc.lessonschedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class LessonInit {

    // Inject repositories
    @Autowired
    LessonJpaRepository repository;

    @Bean
    CommandLineRunner runLesson() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] namesArray = {
                    "Ellen",
                    "Hetvi",
                    "Brian",
                    "Iris",
                    "Iris",
                    "Kian"

            };

            // make sure Joke database is populated with starting jokes

            for (String names : namesArray) {
                List<Lesson> test = repository.findByNameIgnoreCase(names); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Lesson(null, names, 12, "Mechanics",
                            "kian@gmail.com")); // JPA save
            }

        };
    }
}
