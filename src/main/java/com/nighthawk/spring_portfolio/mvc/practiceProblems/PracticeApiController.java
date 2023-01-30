package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/problems")  // all requests in file begin with this URI
public class PracticeApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private PracticeJpaRepository repository;

    /* GET List of Jokes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Practice>> getProblems() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Practice> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Practice> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Practice problems = optional.get();  // value from findByID
            problems.setUnit(problems.getUnit()+1); // increment value
            repository.save(problems);  // save entity
            return new ResponseEntity<>(problems, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Practice> setJeer(@PathVariable long id) {
        Optional<Practice> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Practice problems = optional.get();
            problems.setTopic(problems.getTopic()+1);
            repository.save(problems);
            return new ResponseEntity<>(problems, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/share/{id}")
    public ResponseEntity<Practice> setTags(@PathVariable long id) {
        Optional<Practice> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Practice problems = optional.get();
            problems.setTags(problems.getTags()+1);
            repository.save(problems);
            return new ResponseEntity<>(problems, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
