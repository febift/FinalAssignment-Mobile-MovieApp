package com.example.finalassignment.Response;

import com.example.finalassignment.Model.MovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<MovieModel> movies;

    public List<MovieModel> getMovies() { return movies;}
}
