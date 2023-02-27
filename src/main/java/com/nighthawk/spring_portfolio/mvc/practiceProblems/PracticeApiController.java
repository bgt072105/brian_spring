package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/problems") // all requests in file begin with this URI
public class PracticeApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private PracticeJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Practice>> getProblems() {
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
    public ResponseEntity<Practice> addProblem(@RequestBody final String stringifiedJson){
        try{
            Map<String,String> json = new ObjectMapper().readValue(stringifiedJson, Map.class);
            String problem = json.get("problem");
            int Unit = Integer.parseInt(json.get("Unit"));
            String Topic = json.get("Topic");
            String Tags = json.get("Tags");
            repository.save(new Practice(null, problem, Unit, Topic, Tags)); // JPA save
            long maxId = repository.getMaxId();
            Optional<Practice> optional = repository.findById(maxId);
            if (optional.isPresent()) {
                Practice practice = optional.get();
                return new ResponseEntity(practice, HttpStatus.OK);
            }
        } catch (Exception e) {

        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * GET individual Person using ID
     */
    @PostMapping("/search")
    public ResponseEntity<List<Practice>> getPerson(@RequestBody final String stringifiedJson) {
        try {
            Map<String,String> json = new ObjectMapper().readValue(stringifiedJson, Map.class);
            String term = json.get("term");
            return new ResponseEntity<>(repository.findByProblemOrTag(term), HttpStatus.OK);
        } catch (Exception e) {

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
}
