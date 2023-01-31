package com.nighthawk.spring_portfolio.mvc.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/discussion") // all requests in file begin with this URI
public class DiscussionBoardApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private DiscussionBoardJpaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<DiscussionBoard>> getQuestion() {
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
    public ResponseEntity<DiscussionBoard> setLike(@PathVariable long id) {
        /*
         * Optional (below) is a container object which helps determine if a result is
         * present.
         * If a value is present, isPresent() will return true
         * get() will return the value.
         */
        Optional<DiscussionBoard> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            DiscussionBoard questions = optional.get(); // value from findByID
            questions.setUnit(questions.getUnit() + 1); // increment value
            repository.save(questions); // save entity
            return new ResponseEntity<>(questions, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    @PutMapping("/share/{id}")
    public ResponseEntity<DiscussionBoard> setTags(@PathVariable long id) {
        Optional<DiscussionBoard> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            DiscussionBoard questions = optional.get();
            questions.setTags(questions.getTags() + 1);
            repository.save(questions);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
