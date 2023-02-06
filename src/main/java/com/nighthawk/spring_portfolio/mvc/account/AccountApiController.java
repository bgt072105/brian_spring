package com.nighthawk.team_backend.mvc.database.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/Account")
public class AccountApiController {
    // @Autowired
    // private JwtTokenUtil jwtGen;
    /*
     * #### RESTful API ####
     * Resource: https://spring.io/guides/gs/rest-service/
     */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private AccountDetailsService repository;
    @Autowired
    private AccountJpaRepository jparepository;

    /*
     * GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Account>> getPeople() {
        return new ResponseEntity<>(repository.listAll(), HttpStatus.OK);
    }

    /*
     * GET individual Account using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable long id) {
        Optional<Account> optional = jparepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Account Account = optional.get(); // value from findByID
            return new ResponseEntity<>(Account, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * DELETE individual Account using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable long id) {
        Optional<Account> optional = jparepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Account Account = optional.get(); // value from findByID
            jparepository.deleteById(id); // value from findByID
            return new ResponseEntity<>(Account, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * POST Aa record by Requesting Parameters from URI
     */
    @PostMapping("/post")
    public ResponseEntity<Object> postAccount(@RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {
        // A Account object WITHOUT ID will create a new record with default roles as
        // student
        Account Account = new Account(email, password, name);
        repository.save(Account);
        return new ResponseEntity<>(email + " is created successfully", HttpStatus.CREATED);
    }

    /*
     * The AccountSearch API looks across database for partial match to term (k,v)
     * passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> AccountSearch(@RequestBody final Map<String, String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Account> list = repository.listLike(term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}