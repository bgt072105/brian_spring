package com.nighthawk.spring_portfolio.mvc.lightboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/calculator")
@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class LightBoardApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */


    /*
    GET calculation
     */
    @GetMapping("/calculate/{input}")
    public ResponseEntity<Calculator> calculate(@PathVariable String input) {
        if (input.length() > 0) {  // Good ID
            Calculator operator = new Calculator(input.replace("GG", "/").replace("AA", "%"));
            return new ResponseEntity<>(operator, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

}
