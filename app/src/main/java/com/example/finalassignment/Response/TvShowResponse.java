package com.example.finalassignment.Response;

import com.example.finalassignment.Model.TvShowModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    private List<TvShowModel> tvShows;
    public List<TvShowModel> getTvShows() {return tvShows;}
}
