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
@RequestMapping("/api/lightboard")
@JsonAutoDetect(getterVisibility=Visibility.NONE)
public class LightBoardApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */


    /*
    GET LightBoard
     */
    @GetMapping("/")
    public ResponseEntity<LightBoard> LightBoard() {
        try { 
            LightBoard operator = new LightBoard(5, 5);
            return new ResponseEntity<>(operator, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        }
    }
    
    @GetMapping("/create/{width}/{length}")
    public ResponseEntity<LightBoard> LightBoard(@PathVariable("width") int width, @PathVariable("length") int length) {
        try { 
            LightBoard operator = new LightBoard(width, length);
            return new ResponseEntity<>(operator, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        }
    }
    
    // @GetMapping("/simulate/{generations}")
    // public ResponseEntity<List<LightBoard>> getSimulation(@PathVariable("generations") int generations) {
    //     List<LightBoard> lightBoardList = new ArrayList<LightBoard>();
    //     LightBoard lightBoard = new LightBoard(5,5);
    //     lightBoardList.add(lightBoard);
    //     for (int i = 0; i < generations; i++) {
    //         lightBoard = new LightBoard(lightBoard.nextGeneration());
    //         // debug: print the lightBoard
    //         System.out.println(lightBoard);
    //         // add a copy of the lightBoard to the list
    //         lightBoardList.add(lightBoard);
    //     }
    //     return new ResponseEntity<List<LightBoard>>(lightBoardList, HttpStatus.OK);
    // }
}
