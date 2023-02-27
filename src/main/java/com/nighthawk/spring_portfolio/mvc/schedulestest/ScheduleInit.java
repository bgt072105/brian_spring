package com.nighthawk.spring_portfolio.mvc.schedulestest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ScheduleInit {

    // Inject repositories
    @Autowired
    ScheduleJpaRepository scheduleRepository;

    @Autowired
    PersonJpaRepository personRepository;

    @Bean
    CommandLineRunner runSchedule() { // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // Clear repo
            // repository.deleteAll();

            // Create test note for each person if not already created
            List<Person> people = personRepository.findAll();
            for (Person person : people) {
                if (scheduleRepository.findByPersonId(person.getId()).isEmpty()) {
                    String classname = "Class name for " + person.getName();
                    String period = "Period for " + person.getName();
                    Schedule schedule = new Schedule(classname, period, person);
                    scheduleRepository.save(schedule);
                }
            }
        };
    }
}
