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

@CrossOrigin
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        CustomizedResponse response = new CustomizedResponse("List of users:", service.getUsers());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity getUsers(@PathVariable("email") String email){
        CustomizedResponse response = new CustomizedResponse("User email: "+ email, Collections.singletonList(service.getAUser(email)));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/users/add", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUsers(@RequestBody UserModel user) {
        CustomizedResponse response = new CustomizedResponse("User  created. ", Collections.singletonList(service.addUser(user)));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
