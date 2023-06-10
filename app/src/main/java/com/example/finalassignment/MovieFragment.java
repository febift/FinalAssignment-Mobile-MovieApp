package com.example.finalassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment {
    private RecyclerView rv_movies;
    private List<MovieModel> movies;
    private GridLayoutManager layoutManager;
    private ProgressBar progressBar;
    private TextView tv_no_internet;
    private ImageView btn_retry;
    private Handler handler;
    MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        tv_no_internet = view.findViewById(R.id.tv_no_internet);
        btn_retry = view.findViewById(R.id.btn_retry);
        rv_movies = view.findViewById(R.id.rv_movie);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Movies");

        rv_movies.setHasFixedSize(true);
        rv_movies.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        handler = new Handler(Looper.getMainLooper());
        rv_movies.setVisibility(View.GONE);
        tv_no_internet.setVisibility(View.GONE);
        btn_retry.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Call<MovieResponse> client = ApiConfig.getApiService().getMovies(ApiConfig.getApiKey());
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        movies = response.body().getMovies();
                        MovieAdapter adapter = new MovieAdapter(movies);
                        rv_movies.setAdapter(adapter);
                        rv_movies.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }
                    if (response.body() != null) {
                        Log.e("MovieFragment", "OnFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}