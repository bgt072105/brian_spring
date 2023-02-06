package com.nighthawk.spring_portfolio.mvc.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/student")
public class StudentApiController {

    // inject repo
    @Autowired
    private StudentJpaRepository repository;

    // to home page
    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    /*
     * GET list of people
     */
    @GetMapping("/")
    public ResponseEntity<List<Student>> getNames() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    // search events
    @GetMapping("/event/{event}")
    public ResponseEntity<List<Student>> getEvent(@PathVariable String event) {
        List<Student> people = new ArrayList<Student>();
        for (long i = 1; repository.existsById(i); i++) {
            Optional<Student> optional = repository.findById(i);
            Student person = optional.get();
            if (person.getEvent().contains(event)) {
                people.add(person);

            }
        }
        return new ResponseEntity<>(people, HttpStatus.BAD_REQUEST);
    }

    // add events
    @PutMapping("/addEvent/{id}/{event}")
    public ResponseEntity<Student> setEvent(@PathVariable long id, @PathVariable String event) {

        Optional<Student> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Student person = optional.get();
            person.addEvent(event);
            repository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // remove events
    @PutMapping("/removeEvent/{id}/{event}")
    public ResponseEntity<Student> delEvent(@PathVariable long id, @PathVariable String event) {

        Optional<Student> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Student person = optional.get();

            if (person.getEvent().contains(event)) {
                int index = person.getEvent().indexOf(event);
                person.removeEvent(index);
                repository.save(person);

                return new ResponseEntity<>(person, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // post new student to database
    @PostMapping(path = "/addStudent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> createStudent(@RequestBody Student studentDetails) {

        Student newStudent = repository.save(studentDetails);

        if (newStudent == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        }
    }

    // updates student info
    @PutMapping(path = "/updateStudent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@RequestBody UpdatedStudentData newStudentDetails) {
        // Finds students w/ specified email
        List<Student> existingStudents = repository.findByEmailIgnoreCase(newStudentDetails.getCurrentEmail());

        // checks if student actually exists in db
        if (existingStudents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // picks first student by referencing email (assumes only one instance of
        // student w/ particular
        // email)
        Student student = existingStudents.get(0);

        // updates data for student
        student.setName(newStudentDetails.getName());
        student.setPasswordHash(newStudentDetails.getPasswordHash());
        student.setGraduatingYear(newStudentDetails.getGraduatingYear());
        student.setEmail(newStudentDetails.getEmail());
        student.setPhoneNumber(newStudentDetails.getPhoneNumber());

        // saves updated student to repo, overwrites by id
        Student updatedStudent = repository.save(student);

        // checks if new student is null or not
        if (updatedStudent == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        }
    }

}
