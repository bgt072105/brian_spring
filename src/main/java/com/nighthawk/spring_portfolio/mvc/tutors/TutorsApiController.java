package com.nighthawk.spring_portfolio.mvc.tutors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/tutors") // all requests in file begin with this URI
public class TutorsApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private TutorsJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Tutors>> getTutors() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /*
     * Update Like
     * 
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific
     * handler methods.
     * 
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Tutors> setLike(@PathVariable long id) {
        /*
         * Optional (below) is a container object which helps determine if a result is
         * present.
         * If a value is present, isPresent() will return true
         * get() will return the value.
         */
        Optional<Tutors> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Tutors tutors = optional.get(); // value from findByID
            tutors.setLocation(tutors.getLocation() + 1); // increment value
            repository.save(tutors); // save entity
            return new ResponseEntity<>(tutors, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Tutors> setJeer(@PathVariable long id) {
        Optional<Tutors> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Tutors tutors = optional.get();
            tutors.setExperience(tutors.getExperience() + 1);
            repository.save(tutors);
            return new ResponseEntity<>(tutors, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/share/{id}")
    public ResponseEntity<Tutors> setTags(@PathVariable long id) {
        Optional<Tutors> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Tutors tutors = optional.get();
            tutors.setPastReviews(tutors.getPastReviews() + 1);
            repository.save(tutors);
            return new ResponseEntity<>(tutors, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}