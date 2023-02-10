package com.nighthawk.spring_portfolio.mvc.equations;

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
@RequestMapping("/api/equation")
public class EquationApiController {
    /*
     * #### RESTful API ####
     * Resource: https://spring.io/guides/gs/rest-service/
     */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private EquationJpaRepository repository;

    @Autowired
    private PersonJpaRepository personRepository;

    /*
     * GET List of Equations (for debugging, later on will block this)
     */
     
    @GetMapping("/")
    public ResponseEntity<List<Equation>> getEquations() {
        // ResponseEntity returns List of Equations provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /* Post request to add a new equation */
    @PostMapping("/add")
    public ResponseEntity<Equation> addEquation(@RequestParam("person_id") int person_id, @RequestParam("text") String text) {
        Person person = personRepository.getPersonById(person_id);
        Equation equation = new Equation(text, person);
        repository.save(equation);
        return new ResponseEntity<>(equation, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
    }


    /*
     * GET individual Person equations using ID
     */
    // @GetMapping("/{id}")
    // public ResponseEntity<List<Equation>> getEquations(@PathVariable long person_id) {
    //     Optional<List<Equation>> optional = repository.findAllById(id);
    //     if (optional.isPresent()) { // Good ID
    //         List<Equation> equations = optional.get(); // value from findByID
    //         return new ResponseEntity<>(equations, HttpStatus.OK); // OK HTTP response: status code, headers, and body
    //     }
    //     // Bad ID
    //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }

    // CRUD operations
}