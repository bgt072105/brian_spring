package com.nighthawk.spring_portfolio.mvc.tutorDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/tutor") // all requests in file begin with this URI
public class TutorApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private TutorJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Tutor>> getQuestion() {
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
    public ResponseEntity<Tutor> addQuestion(@RequestParam("question") String question,
            @RequestParam("Unit") int Unit,
            @RequestParam("Tags") String Tags,
            @RequestParam("Email") String Email) {
        repository.save(new Tutor(null, question, Unit, Tags, Email)); // JPA save
        long maxId = repository.getMaxId();
        Optional<Tutor> optional = repository.findById(maxId);
        if (optional.isPresent()) {
            Tutor question1 = optional.get();
            return new ResponseEntity(question1, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * GET individual Person using ID
     */
    @GetMapping("/search")
    public ResponseEntity<List<Tutor>> getPerson(@RequestParam("question") String term) {
        return new ResponseEntity<>(repository.findByQuestionorEmail(term), HttpStatus.OK);
    }

    /*
     * Update Jeer
     */
    @PostMapping("/jeer/{id}")
    public ResponseEntity<Tutor> setJeer(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/share/{id}")
    public ResponseEntity<Tutor> setTags(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}