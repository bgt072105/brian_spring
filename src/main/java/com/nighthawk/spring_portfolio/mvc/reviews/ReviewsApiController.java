package com.nighthawk.spring_portfolio.mvc.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/reviews") // all requests in file begin with this URI
public class ReviewsApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private ReviewsJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Reviews>> getReviewername() {
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
    @PostMapping("/add")
    public ResponseEntity<Reviews> addReviewername(@RequestParam("name") String reviewername,
            @RequestParam("Age") int age,
            @RequestParam("Review") String reviewtext,
            @RequestParam("Contact") String contact) {
        repository.save(new Reviews(null, reviewername, age, reviewtext, contact)); // JPA save
        long maxId = repository.getMaxId();
        Optional<Reviews> optional = repository.findById(maxId);
        if (optional.isPresent()) {
            Reviews reviews1 = optional.get();
            return new ResponseEntity(reviews1, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * GET individual Person using ID
     */
    @GetMapping("/search")
    public ResponseEntity<List<Reviews>> getPerson(@RequestParam("name") String term) {
        return new ResponseEntity<>(repository.findByReviewernameorContact(term), HttpStatus.OK);
    }

    /*
     * Update Jeer
     */
    @PostMapping("/jeer/{id}")
    public ResponseEntity<Reviews> setJeer(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/share/{id}")
    public ResponseEntity<Reviews> setTags(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
