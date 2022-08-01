package com.example.restapimongodbypan63.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepo extends MongoRepository<Media, String> {
}
