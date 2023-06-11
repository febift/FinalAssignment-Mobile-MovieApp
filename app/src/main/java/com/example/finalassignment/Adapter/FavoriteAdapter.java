package com.example.finalassignment.Adapter;

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
import com.example.finalassignment.DetailActivity;
import com.example.finalassignment.Model.MovieModel;
import com.example.finalassignment.R;
import com.example.finalassignment.Model.TvShowModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Object> favoriteList;

    public FavoriteAdapter(List<Object> favoriteList) {
        this.favoriteList = favoriteList;
    }
    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Object favorite = favoriteList.get(position);

        if (favorite instanceof MovieModel){
            MovieModel movie = (MovieModel) favorite;
            holder.tv_title.setText(movie.getTitle());
            holder.iv_category_icon.setImageResource(R.drawable.baseline_movie_24);
            holder.tv_year.setText(movie.getRelease_date().substring(0,4));
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(holder.iv_cover);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                holder.itemView.getContext().startActivity(intent);
            });
        } else if (favorite instanceof TvShowModel) {
            TvShowModel tvShow = (TvShowModel) favorite;

            holder.tv_title.setText(tvShow.getName());
            holder.iv_category_icon.setImageResource(R.drawable.baseline_tv_24);

            holder.tv_year.setText(tvShow.getFirst_air_date().substring(0,4));
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + tvShow.getPoster_image())
                    .apply(new RequestOptions().override(350, 350))
                    .into(holder.iv_cover);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TVSHOW, tvShow);
                holder.itemView.getContext().startActivity(intent);
            });
        }


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_year;
        ImageView iv_cover, iv_category_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_year = itemView.findViewById(R.id.tv_year);
            iv_cover = itemView.findViewById(R.id.iv_cover);
            iv_category_icon = itemView.findViewById(R.id.icon_item);
        }
    }
}
