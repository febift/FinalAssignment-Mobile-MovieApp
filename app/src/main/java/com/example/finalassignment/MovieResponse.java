package com.example.finalassignment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<MovieModel> movies;

    public List<MovieModel> getMovies() { return movies;}
}
