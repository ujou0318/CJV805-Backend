package com.example.restapimongodbypan63.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("media")
public class Media {

    @Id
    private String id;
    private String title;
    private Integer year;
    private Double buy;
    private Double rent;
    private Integer seasons;
    private Integer episodes;
    private String img;
    private String desc;
    private Boolean isFeatured;
    private Boolean isMovie;

    @Override
    public String toString() {
        return "MediaModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", buy=" + buy +
                ", rent=" + rent +
                ", seasons=" + seasons +
                ", episodes=" + episodes +
                ", img='" + img + '\'' +
                ", desc='" + desc + '\'' +
                ", isFeatured=" + isFeatured +
                ", isMovie=" + isMovie +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public Boolean getMovie() {
        return isMovie;
    }

    public void setMovie(Boolean movie) {
        isMovie = movie;
    }

    public Media(String id, String title, Integer year, Double buy, Double rent, Integer seasons, Integer episodes, String img, String desc, Boolean isFeatured, Boolean isMovie) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.buy = buy;
        this.rent = rent;
        this.seasons = seasons;
        this.episodes = episodes;
        this.img = img;
        this.desc = desc;
        this.isFeatured = isFeatured;
        this.isMovie = isMovie;


    }
}
