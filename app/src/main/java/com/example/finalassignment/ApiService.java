package com.example.finalassignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<MovieResponse> getMovies(@Query("api_key") String key);

    @GET("tv/popular")
    Call<TvShowResponse> getTvShows(@Query("api_key") String key);
}
