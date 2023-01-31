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
    CommandLineRunner runDiscussionBoard() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] questionsArray = {
                    "Two students, S1 and S2, are rushing to class. They are waiting for green light at the same intersection. As soon as the light turns green, both students start walking with constant acceleration. The acceleration of student S1 is a1, and the acceleration of student S2 is a2, When student S1 has reached a velocity v1, she has walked a distance D1, while student S2 has walked a distance D2 = 1/3D1. In terms of v1 and D1, what is the correct expression for a2, the acceleration of student S2?",
                    "Copenhagen is located 780 km North and 810 km East of Paris. A flight from Paris to Copenhagen takes two hours. Typically the wind blows from East to West over Europe, but the speed varies. For one particular flight, the wind speed was 165 km/h. What is the magnitude of vpa, the plane's velocity with respect to the air?",
            };

            // make sure Joke database is populated with starting jokes
            for (String questions : questionsArray) {
                List<DiscussionBoard> test = repository.findByProblemIgnoreCase(questions); // JPA lookup
                if (test.size() == 0)
                    repository.save(new DiscussionBoard(null, questions, "1.1 Newton's Laws",
                            "physics, newton's laws, force, mass, acceleration")); // JPA save
            }

        };
    }
}
