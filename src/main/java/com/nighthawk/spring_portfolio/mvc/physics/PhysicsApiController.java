package com.nighthawk.spring_portfolio.mvc.physics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/physics")  // all requests in file begin with this URI
public class PhysicsApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private PhysicsJpaRepository repository;

    /* GET List of Physics
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Physics>> getPhysics() {
        // ResponseEntity returns List of Physics provide by JPA findAll()
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Physics> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Physics> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Physics equation = optional.get();  // value from findByID
            equation.setLike(equation.getLike()+1); // increment value
            repository.save(equation);  // save entity
            return new ResponseEntity<>(equation, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Dislike
     */
    @PutMapping("/dislike/{id}")
    public ResponseEntity<Physics> setDislike(@PathVariable long id) {
        Optional<Physics> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Physics equation = optional.get();
            equation.setDislike(equation.getDislike()+1);
            repository.save(equation);
            return new ResponseEntity<>(equation, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // @PutMapping("/share/{id}")
    // public ResponseEntity<Physics> setShare(@PathVariable long id) {
    //     Optional<Physics> optional = repository.findById(id);
    //     if (optional.isPresent()) {  // Good ID
    //         Physics equation = optional.get();
    //         equation.setShare(equation.getShare()+1);
    //         repository.save(equation);
    //         return new ResponseEntity<>(equation, HttpStatus.OK);
    //     }
    //     // Bad ID
    //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
}
