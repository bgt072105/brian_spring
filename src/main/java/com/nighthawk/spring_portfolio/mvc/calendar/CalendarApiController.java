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
    public String calendar() {

        // load HTML VIEW (calendar.html)
        return "calendar";

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