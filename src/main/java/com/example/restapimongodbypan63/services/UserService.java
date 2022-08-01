package com.example.restapimongodbypan63.services;

import com.example.restapimongodbypan63.models.UserModel;
import com.example.restapimongodbypan63.models.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPwdEncoder;

    public UserModel addUser(UserModel user){

        String encryptedPwd = bCryptPwdEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        UserModel insertUser = userRepo.insert(user);
        return insertUser;
    }

    public List<UserModel> getUsers(){
        return userRepo.findAll();
    }

    public Optional<UserModel> getAUser(String id){
        return userRepo.findById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel existedUser = userRepo.findByEmail(email);
        System.out.println(existedUser);
        String eml = existedUser.getEmail();
        String pwd = existedUser.getPassword();
        return new User(eml, pwd, new ArrayList<>());
    }
}
