package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    private ImageView btn_back, btn_favorite, iv_cover, iv_background, iv_category_icon;
    private TextView tv_title, tv_synopsis, tv_release_date, tv_rating, tv_popularity, tv_language;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MovieModel movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TvShowModel tvShowModel = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        btn_back = findViewById(R.id.btn_back);
        btn_favorite = findViewById(R.id.btn_favorite);
        iv_cover = findViewById(R.id.iv_cover);
        iv_background = findViewById(R.id.iv_background);
        tv_title = findViewById(R.id.tv_title);
        tv_synopsis = findViewById(R.id.tv_synopsis);
        tv_release_date = findViewById(R.id.tv_release_date);
        tv_rating = findViewById(R.id.tv_rating);
        tv_popularity = findViewById(R.id.tv_popularity);
        tv_language = findViewById(R.id.tv_language);
        iv_category_icon = findViewById(R.id.iv_category_icon);


        btn_back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        if (movieModel != null) {
            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" +movieModel.getPoster_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(iv_cover);
            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" +movieModel.getBackdrop_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(iv_background);
            tv_title.setText(movieModel.getTitle());
            tv_synopsis.setText(movieModel.getSynopsis());
            tv_release_date.setText(movieModel.getRelease_date());
            tv_rating.setText(String.valueOf(movieModel.getRating()));
            tv_popularity.setText("Popularity : " + movieModel.getPopularity());
            tv_language.setText("Language : " + movieModel.getLanguage());
            iv_category_icon.setImageResource(R.drawable.baseline_movie_24);
        }
        if (tvShowModel != null) {
            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" + tvShowModel.getPoster_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(iv_cover);
            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" + tvShowModel.getBackdrop_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(iv_background);
            tv_title.setText(tvShowModel.getName());
            tv_synopsis.setText(tvShowModel.getSynopsis());
            tv_release_date.setText(tvShowModel.getFirst_air_date());
            tv_rating.setText(String.valueOf(tvShowModel.getRating()));
            tv_popularity.setText("Popularity : " + tvShowModel.getPopularity());
            tv_language.setText("Language : " + tvShowModel.getLanguage());
            iv_category_icon.setImageResource(R.drawable.baseline_tv_24);
        }
    }
}