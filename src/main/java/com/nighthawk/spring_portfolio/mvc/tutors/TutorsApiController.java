package com.nighthawk.spring_portfolio.mvc.tutors;

import org.codehaus.groovy.transform.TupleConstructorASTTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.micrometer.core.instrument.internal.TimedExecutorService;

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
    public ResponseEntity<List<Tutors>> getProblems() {
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
    @PostMapping(value = "/add")
    public ResponseEntity<TutorsApiController> addProblem(@RequestParam("question") String problem,
            @RequestParam("Unit") int Unit,
            @RequestParam("Topic") String Topic,
            @RequestParam("Tags") String Tags) {
        repository.save(new Tutors(null, problem, Unit, Topic, Tags)); // JPA save
        long maxId = repository.getMaxId();
        Optional<Tutors> optional = repository.findById(maxId);
        if (optional.isPresent()) {
            Tutors practice = optional.get();
            return new ResponseEntity(practice, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Tutors> setJeer(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/share/{id}")
    public ResponseEntity<Tutors> setTags(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
