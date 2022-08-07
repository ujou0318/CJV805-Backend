package com.example.restapimongodbypan63.control;

import com.example.restapimongodbypan63.CustomizedResponse;
import com.example.restapimongodbypan63.models.UserModel;
import com.example.restapimongodbypan63.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity loggin(@RequestBody UserModel user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            CustomizedResponse response = new CustomizedResponse("Login Sucessed.", Collections.singletonList(service.loadUserByUsername(user.getEmail())));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            CustomizedResponse responses = new CustomizedResponse("Your email or passowrd were incorrect.", null);
            return new ResponseEntity(responses, HttpStatus.UNAUTHORIZED);
        }
    }
}
