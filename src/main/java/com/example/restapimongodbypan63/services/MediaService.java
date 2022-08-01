package com.example.restapimongodbypan63.services;


import com.example.restapimongodbypan63.models.Media;
import com.example.restapimongodbypan63.models.MediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepo repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Media media;

    public List<Media> getAll(){
        return repo.findAll();
    }

    public List<Media> getMovies() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isMovie").is(true));
        List<Media> movies = mongoTemplate.find(query, Media.class);
        return movies;
    }

    public List<Media> getFeaturedMovies(){
        Query query = new Query();
        query.addCriteria(Criteria.where("isFeatured").is(true));
        query.addCriteria(Criteria.where("isMovie").is(true));
        List<Media> featuredShows = mongoTemplate.find(query, Media.class);
        return featuredShows;
    }

    public List<Media> getShows() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isMovie").is(false));
        List<Media> shows = mongoTemplate.find(query, Media.class);
        return shows;
    }

    public List<Media> getFeaturedShows(){
        Query query = new Query();
        query.addCriteria(Criteria.where("isFeatured").is(true));
        query.addCriteria(Criteria.where("isMovie").is(false));
        List<Media> featuredShows = mongoTemplate.find(query, Media.class);
        return featuredShows;
    }

    public List<Media> getMediaByKeyword(String keyword){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(keyword, "i"));
        List<Media> media = mongoTemplate.find(query, Media.class);
        return media;
    }


    public void insertIntoMedia (Media media){
        repo.insert(media);
    }

    public Media editMedia(String id, Media newData)
    {
        Optional<Media> media = repo.findById(id);
        media.get().setTitle(newData.getTitle());
        media.get().setYear(newData.getYear());
        media.get().setBuy(newData.getBuy());
        media.get().setRent(newData.getRent());
        media.get().setSeasons(newData.getSeasons());
        media.get().setEpisodes(newData.getEpisodes());
        media.get().setImg(newData.getImg());
        media.get().setDesc(newData.getDesc());
        media.get().setFeatured(newData.getFeatured());
        media.get().setMovie(newData.getMovie());

        Media updateMovie = repo.save(media.get());
        return updateMovie;



    }

    public Optional<Media> getAMedia(String id) throws Exception
    {
        Optional<Media> media = repo.findById(id);
        if (!media.isPresent())
        {
            throw new Exception ("Media with " + id + " does not exist in our database!");
        }
        return media;
    }

    public void deleteAMedia(String id)
    {
        repo.deleteById(id);
    }
}
