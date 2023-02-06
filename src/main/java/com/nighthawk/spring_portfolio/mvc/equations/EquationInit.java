
package com.nighthawk.spring_portfolio.mvc.equations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class EquationInit {

    // Inject repositories
    @Autowired
    EquationJpaRepository repository;

    @Autowired
    PersonJpaRepository personRepository;

    @Bean
    CommandLineRunner runEquation() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // Create test note for each person
            List<Person> people = personRepository.findAll();
            for (Person person : people) {
                String text = "Equation for " + person.getName();
                Equation equation = new Equation(text, person);
                repository.save(equation);
            }

        };
    }
}
