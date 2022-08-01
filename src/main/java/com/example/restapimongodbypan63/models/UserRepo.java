package com.example.restapimongodbypan63.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserModel, String> {

    UserModel findByEmail(String email);
}
