package com.nighthawk.spring_portfolio.mvc.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<List<DiscussionBoard>> getQuestions() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DiscussionBoard> addQuestion(@RequestParam("Question") String Question,
            @RequestParam("Unit") String Unit,
            @RequestParam("Tags") String Tags) {
        repository.saveAll(new DiscussionBoard(object: null, Question, Unit, Tags)); // JPA save
        long maxId = repository.getMaxId();
        Optional<DiscussionBoard> optional = repository.findById(maxId);
        if (optional.isPresent()) {
            DiscussionBoard discussion = optional.get();
            return new ResponseEntity(discussion, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * GET individual Person using ID
     */
    @GetMapping("/search")
    public ResponseEntity<List<DiscussionBoard>> getPerson(@RequestParam("question") String term) {
        return new ResponseEntity<>(repository.findByQuestionOrTag(term), HttpStatus.OK);
    }

    /*
     * Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<DiscussionBoard> setJeer(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/share/{id}")
    public ResponseEntity<DiscussionBoard> setTags(@PathVariable long id) {
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
