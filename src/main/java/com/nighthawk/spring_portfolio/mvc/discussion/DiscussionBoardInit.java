package com.nighthawk.spring_portfolio.mvc.discussion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class DiscussionBoardInit {

    // Inject repositories
    @Autowired
    DiscussionBoardJpaRepository repository;

    @Bean
    CommandLineRunner runQuestions() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] questionArray = {
                    "What is the formula for calculating kinetic energy?", "ahfha", "adkfad", "dsfkas",
                    "How is kinetic energy converted to potential energy?", "ahfha", "adkfad", "dsfkas",
            };

            // make sure Joke database is populated with starting jokes
            for (String Question : questionArray) {
                List<DiscussionBoard> test = repository.findByQuestionIgnoreCase(Question); // JPA lookup
                if (test.size() == 0)
                    repository.save(new DiscussionBoard(Question, "rube goldberg", "1.1 Newton's Laws",
                            "physics, newton's laws, force, mass, acceleration")); // JPA save
            }

        };
    }
}
