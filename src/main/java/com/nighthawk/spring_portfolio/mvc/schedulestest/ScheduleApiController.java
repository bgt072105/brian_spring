package com.nighthawk.spring_portfolio.mvc.schedulestest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/classes")
public class ScheduleApiController {
    /*
     * #### RESTful API ####
     * Resource: https://spring.io/guides/gs/rest-service/
     */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private ScheduleJpaRepository repository;

    @Autowired
    private PersonJpaRepository personRepository;

    /*
     * GET List of Equations (for debugging, later on will block this)
     */

    @GetMapping("/")
    public ResponseEntity<List<Schedule>> getSchedules() {
        // ResponseEntity returns List of Equations provide by JPA findAll()
        // TODO: include foreign key person_id
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /*
     * GET individual Person equations using ID
     */
    @GetMapping("/{person_id}")
    public ResponseEntity<List<Schedule>> getSchedules(@PathVariable long person_id) {
        Optional<List<Schedule>> optional = repository.findByPersonId(person_id);
        if (optional.isPresent()) { // Good ID
            List<Schedule> schedules = optional.get(); // value from findByID
            return new ResponseEntity<>(schedules, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // CRUD operations
    /* Post request to add a new equation */
    @PostMapping("/add")
    public ResponseEntity<Schedule> addSchedule(@RequestParam("person_id") long person_id,
            @RequestParam("classname") String classname,
            @RequestParam("period") String period) {
        Optional<Person> optional = personRepository.findById(person_id);
        if (optional.isPresent()) { // Good ID
            Person person = optional.get(); // value from findByID
            Schedule schedule = new Schedule(classname, period, person);
            repository.save(schedule);
            // return response in json format
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete/{person_id}/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable long person_id, @PathVariable long id) {
        Optional<Schedule> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            // only allow to delete if the equation belongs to the person
            if (optional.get().getPerson().getId() == person_id) {
                Schedule schedule = optional.get(); // value from findByID
                repository.deleteById(id); // value from findByID
                return new ResponseEntity<>(schedule, HttpStatus.OK); // OK HTTP response: status code, headers, and
                                                                      // body
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
