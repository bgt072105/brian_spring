package com.nighthawk.spring_portfolio.mvc.students;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class StudentInit {

    // Inject repositories
    @Autowired
    StudentJpaRepository repository;

    @Bean
    CommandLineRunner runScioly() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // 2d array for names and graduating year
            final String[][] peopleArray = {
                    { "Don Tran", "2024", "donqt15@gmail.com", "858-900-8045" },
                    { "Krish Patil", "2024", "n/a", "n/a" },
                    { "Bailey Say", "2024", "n/a", "n/a" },
                    { "Rohan Gaikwad", "2024", "n/a", "n/a" },
                    { "Andrew Meng", "2024", "n/a", "n/a" },
                    { "Nicolas Mounier", "2024", "n/a", "n/a" },
                    { "Nicholas Ramos", "2024", "n/a", "n/a" },
                    { "Joe Jack Bill", "2024", "n/a", "n/a" }
            };

            // make sure people array database is populated with starting values for members
            for (int i = 0; i < peopleArray.length; i++) {
                String name = peopleArray[i][0];
                int graduatingYear = Integer.parseInt(peopleArray[i][1]);
                String email = peopleArray[i][2];
                String phoneNumber = peopleArray[i][3];
                List<Student> test = repository.findByNameIgnoreCase(name); // JPA lookup
                if (test.size() == 0)
                    repository.save(new Student(null, name, "", new ArrayList<String>(), graduatingYear, email,
                            phoneNumber)); // JPA save
            }

        };
    }
}