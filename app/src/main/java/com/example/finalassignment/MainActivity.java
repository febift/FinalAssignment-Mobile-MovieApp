package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.finalassignment.Fragment.FavoriteFragment;
import com.example.finalassignment.Fragment.MovieFragment;
import com.example.finalassignment.Fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    private ImageView btn_movie, btn_tv_show, btn_favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_movie = findViewById(R.id.btn_movie);
        btn_tv_show = findViewById(R.id.btn_tv_show);
        btn_favorite = findViewById(R.id.btn_favorite);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieFragment movieFragment = new MovieFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());

        if (!(fragment instanceof MovieFragment)) {
            fragmentManager.beginTransaction()
                    .add(R.id.frame_layout, movieFragment, MovieFragment.class.getSimpleName())
                    .commit();
        }
        btn_movie.setOnClickListener(view -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, movieFragment, MovieFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        btn_tv_show.setOnClickListener(view -> {
            TvShowFragment tvShowFragment = new TvShowFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, tvShowFragment, TvShowFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        btn_favorite.setOnClickListener(view -> {
            FavoriteFragment favoriteFragment = new FavoriteFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, favoriteFragment, FavoriteFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });


    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



    }