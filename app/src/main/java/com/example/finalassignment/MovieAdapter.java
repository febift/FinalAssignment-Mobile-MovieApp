package com.example.finalassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
//    Context context;
    private List<MovieModel> movies;

    public MovieAdapter(List<MovieModel> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieModel movieModel = movies.get(position);
        holder.tv_title.setText(movieModel.getTitle());
        holder.tv_year.setText(movieModel.getRelease_date().substring(0, 4));
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_image())
                .apply(new RequestOptions().override(350, 350))
                .into(holder.iv_cover);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_MOVIE, movieModel.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_cover;
        TextView tv_title, tv_year, tv_rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cover = itemView.findViewById(R.id.iv_cover);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_year = itemView.findViewById(R.id.tv_year);
        }
    }
}
