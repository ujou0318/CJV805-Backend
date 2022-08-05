package com.example.restapimongodbypan63.control;

import com.example.restapimongodbypan63.CustomizedResponse;
import com.example.restapimongodbypan63.models.Media;
import com.example.restapimongodbypan63.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://cjv805-assignment1-2022summer.herokuapp.com/")
@RestController
public class MediaController {

    @Autowired
    private MediaService service;

    @GetMapping("/movies")
    public ResponseEntity getMovies(){
        CustomizedResponse response = new CustomizedResponse("List of movies:", service.getMovies());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/movies/featured")
    public ResponseEntity getFeaturedMovies(){
        CustomizedResponse response = new CustomizedResponse("List of Featured movies:", service.getFeaturedMovies());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/shows")
    public ResponseEntity getShows(){
        CustomizedResponse response = new CustomizedResponse("List of shows:", service.getShows());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/shows/featured")
    public ResponseEntity getFeaturedShows(){
        CustomizedResponse response = new CustomizedResponse("List of Featured shows:", service.getFeaturedShows());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/media/{id}")
    public ResponseEntity getAMedia(@PathVariable("id") String id) {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Media this id: " + id , Collections.singletonList(service.getAMedia(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/media/keyword")
    public ResponseEntity getMediaByKeyword(@RequestParam(value = "name") String k)
    {

        CustomizedResponse customizedResponse = new CustomizedResponse(" A list of movies with the keyword" , service.getMediaByKeyword(k));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }



    @DeleteMapping("/media/{id}")
    public ResponseEntity deleteAMedia(@PathVariable("id") String id)
    {
        service.deleteAMedia(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/media", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMedia(@RequestBody Media media)
    {
        service.insertIntoMedia(media);
        return new ResponseEntity(media, HttpStatus.OK);
    }

    @PutMapping(value = "/media/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editMedia(@PathVariable("id") String id, @RequestBody Media newMedia )
    {
        CustomizedResponse customizedResponse = new CustomizedResponse("Media: " + id + " updated." , Collections.singletonList(service.editMedia(id, newMedia)));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
