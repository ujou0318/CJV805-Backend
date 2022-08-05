package com.example.restapimongodbypan63.control;

import com.example.restapimongodbypan63.CustomizedResponse;
import com.example.restapimongodbypan63.models.UserModel;
import com.example.restapimongodbypan63.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://cjv805-assignment1-2022summer.herokuapp.com/")
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        var response = new CustomizedResponse("List of users:", service.getUsers());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUsers(@PathVariable("id") String id){
        var response = new CustomizedResponse("User ID: "+id, Collections.singletonList(service.getAUser(id)));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/users/add", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUsers(@RequestBody UserModel user) {
        var response = new CustomizedResponse("User  created. ", Collections.singletonList(service.addUser(user)));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
