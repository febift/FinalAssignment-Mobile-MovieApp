package com.example.finalassignment;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdrop_image;
    @SerializedName("poster_path")
    private String poster_image;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("vote_average")
    private Float rating;
    @SerializedName("overview")
    private String synopsis;
    @SerializedName("original_language")
    private String language;
    @SerializedName("popularity")
    private Number popularity;

    public MovieModel(int id, String title, String backdrop_image, String poster_image, String release_date, Float rating, String synopsis, String language, Number popularity) {
        this.id = id;
        this.title = title;
        this.backdrop_image = backdrop_image;
        this.poster_image = poster_image;
        this.release_date = release_date;
        this.rating = rating;
        this.synopsis = synopsis;
        this.language = language;
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_image() {
        return backdrop_image;
    }

    public void setBackdrop_image(String backdrop_image) {
        this.backdrop_image = backdrop_image;
    }

    public String getPoster_image() {
        return poster_image;
    }

    public void setPoster_image(String poster_image) {
        this.poster_image = poster_image;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }
}
