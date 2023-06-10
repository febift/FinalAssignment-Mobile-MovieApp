package com.example.finalassignment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    private List<TvShowModel> tvShows;
    public List<TvShowModel> getTvShows() {return tvShows;}
}
