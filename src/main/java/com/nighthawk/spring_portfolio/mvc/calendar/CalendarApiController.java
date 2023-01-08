package com.nighthawk.spring_portfolio.mvc.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping("/api/calendar")
public class CalendarApiController {

    @GetMapping("/isLeapYear/{year}")
    public ResponseEntity<JsonNode> getIsLeapYear(@PathVariable int year) throws JsonMappingException, JsonProcessingException {
      // Backend Year Object
      Year year_obj = new Year();
      year_obj.setYear(year);  // evaluates Leap Year

      // Turn Year Object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(year_obj.isLeapYearToString()); // this requires exception handling

      return ResponseEntity.ok(json);  // JSON response, see ExceptionHandlerAdvice for throws
    }
    
    @GetMapping("/firstDayOfYear/{year}")
    public ResponseEntity<JsonNode> getfirstDayOfYear(@PathVariable int year) throws
    JsonMappingException,
    JsonProcessingException {
        // Backend Year Object
        Year yearX = new Year();
        yearX.setYear(year);
        // Turn Year Object into JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(yearX.firstDayOfYearToString(year));
        return ResponseEntity.ok(json);
    }
}