package com.example.restapimongodbypan63.control;

import com.example.restapimongodbypan63.CustomizedResponse;
import com.example.restapimongodbypan63.models.UserModel;
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


@CrossOrigin(origins = "http://cjv805-assignment1-2022summer.herokuapp.com/")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity loggin(@RequestBody UserModel user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            CustomizedResponse response = new CustomizedResponse("Login Sucessed.", null);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            CustomizedResponse responses = new CustomizedResponse("Your email or passowrd were incorrect.", null);
            return new ResponseEntity(responses, HttpStatus.UNAUTHORIZED);
        }
    }
}
