package com.nighthawk.spring_portfolio.mvc.tutors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/tutor")
public class TutorApiController {

    // inject repo
    @Autowired
    private TutorJpaRepository repository;

    // to home page
    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    /*
     * GET list of people
     */
    @GetMapping("/")
    public ResponseEntity<List<Tutor>> getNames() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    // search events
    @GetMapping("/experience/{event}")
    public ResponseEntity<List<Tutor>> getEvent(@PathVariable String experience) {
        List<Tutor> people = new ArrayList<Tutor>();
        for (long i = 1; repository.existsById(i); i++) {
            Optional<Tutor> optional = repository.findById(i);
            Tutor person = optional.get();
            if (person.getExperience().contains(experience)) {
                people.add(person);

            }
        }
        return new ResponseEntity<>(people, HttpStatus.BAD_REQUEST);
    }

    // add experience
    @PutMapping("/addExperience/{id}/{experience}")
    public ResponseEntity<Tutor> setEvent(@PathVariable long id, @PathVariable String experience) {

        Optional<Tutor> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Tutor person = optional.get();
            person.addExperience(experience);
            repository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // remove experience
    @PutMapping("/removeExperience/{id}/{event}")
    public ResponseEntity<Tutor> delEvent(@PathVariable long id, @PathVariable String experience) {

        Optional<Tutor> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Tutor person = optional.get();

            if (person.getExperience().contains(experience)) {
                int index = person.getExperience().indexOf(experience);
                person.removeExperience(index);
                repository.save(person);

                return new ResponseEntity<>(person, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // post new student to database
    @PostMapping(path = "/addTutor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutor> createTutor(@RequestBody Tutor tutorDetails) {

        Tutor newTutor = repository.save(tutorDetails);

        if (newTutor == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(newTutor, HttpStatus.CREATED);
        }
    }

    // updates student info
    @PutMapping(path = "/updateTutor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutor> updateStudent(@RequestBody UpdatedTutorData newTutorDetails) {
        // Finds students w/ specified email
        List<Tutor> existingTutors = repository.findByEmailIgnoreCase(newTutorDetails.getCurrentEmail());

        // checks if student actually exists in db
        if (existingTutors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // picks first student by referencing email (assumes only one instance of
        // student w/ particular
        // email)
        Tutor tutor = existingTutors.get(0);

        // updates data for student
        tutor.setName(newTutorDetails.getName());
        tutor.setPasswordHash(newTutorDetails.getPasswordHash());
        tutor.setAge(newTutorDetails.getAge());
        tutor.setEmail(newTutorDetails.getEmail());
        tutor.setMeetingpreference(newTutorDetails.getMeetingpreference());

        // saves updated student to repo, overwrites by id
        Tutor updatedTutor = repository.save(tutor);

        // checks if new student is null or not
        if (updatedTutor == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(updatedTutor, HttpStatus.OK);
        }
    }

}
