package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finalassignment.Database.DatabaseHelper;
import com.example.finalassignment.Model.MovieModel;
import com.example.finalassignment.Model.TvShowModel;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    private ImageView btn_back, btn_favorite, iv_cover, iv_background, iv_category_icon;
    private TextView tv_title, tv_synopsis, tv_release_date, tv_rating, tv_popularity, tv_language;
    private boolean isFavorite;
    DatabaseHelper databaseHelper;
    MovieModel movieModel;
    TvShowModel tvShowModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvShowModel = getIntent().getParcelableExtra(EXTRA_TVSHOW);

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

        getSupportActionBar().hide();


         databaseHelper = new DatabaseHelper(this);

        isFavorite = isItemFavorite();

        setFavoriteButtonState(isFavorite);

        btn_back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        if (movieModel != null) {
            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(iv_cover);
            Glide.with(DetailActivity.this)
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.getBackdrop_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(iv_background);
            tv_title.setText(movieModel.getTitle());
            tv_synopsis.setText(movieModel.getSynopsis());
            tv_release_date.setText(movieModel.getRelease_date().substring(0,4));
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
            tv_release_date.setText(tvShowModel.getFirst_air_date().substring(0,4));
            tv_rating.setText(String.valueOf(tvShowModel.getRating()));
            tv_popularity.setText("Popularity : " + tvShowModel.getPopularity());
            tv_language.setText("Language : " + tvShowModel.getLanguage());
            iv_category_icon.setImageResource(R.drawable.baseline_tv_24);
        }
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    // Menghapus dari favorit jika sudah dalam status favorit
                    if (movieModel != null) {
                        databaseHelper.removeFromFavorites(movieModel.getId());
                        Toast.makeText(DetailActivity.this, "Movie successfully removed from favorites", Toast.LENGTH_SHORT).show();

                    } else if (tvShowModel != null) {
                        databaseHelper.removeFromFavorites(tvShowModel.getId());
                        Toast.makeText(DetailActivity.this, "Tv Show successfully removed from favorites", Toast.LENGTH_SHORT).show();

                    }
                    setFavoriteButtonState(false);
                } else {
                    if (movieModel != null) {
                        databaseHelper.addMovieToFavorites(movieModel, "movie");
                        Toast.makeText(DetailActivity.this, "Movie added successfully", Toast.LENGTH_SHORT).show();

                    } else if (tvShowModel != null) {
                        databaseHelper.addTvShowToFavorites(tvShowModel, "tvshow");
                        Toast.makeText(DetailActivity.this, "Tv Show added successfully", Toast.LENGTH_SHORT).show();
                    }

                    // Mengubah status isFavorite
                    isFavorite = !isFavorite;

                    setFavoriteButtonState(true);

                }
            }
        });


    }
    private boolean isItemFavorite() {
        int id_movie = movieModel != null ? movieModel.getId() : 0;
        int id_tvshow = tvShowModel != null ? tvShowModel.getId() : 0;
        return databaseHelper.isFavorite(id_movie, id_tvshow);
    }

    private void setFavoriteButtonState(boolean isFavorite) {
        if (isFavorite) {
            btn_favorite.setImageResource(R.drawable.baseline_favorite_24);
        } else {
            btn_favorite.setImageResource(R.drawable.baseline_favorite_border_24);
        }


    }
}