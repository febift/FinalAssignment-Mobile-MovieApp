package com.example.finalassignment.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.example.finalassignment.Networking.ApiConfig;
import com.example.finalassignment.MainActivity;
import com.example.finalassignment.R;
import com.example.finalassignment.Adapter.TvShowAdapter;
import com.example.finalassignment.Model.TvShowModel;
import com.example.finalassignment.Response.TvShowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowFragment extends Fragment {
    private RecyclerView rv_tv_show;
    private List<TvShowModel> tvShows;
    private ProgressBar progressBar;
    private TextView tv_no_internet;
    private ImageView btn_retry;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        tv_no_internet = view.findViewById(R.id.tv_no_internet);
        btn_retry = view.findViewById(R.id.btn_retry);
        rv_tv_show = view.findViewById(R.id.rv_tv_show);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tv Show");

        rv_tv_show.setHasFixedSize(true);
        rv_tv_show.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        handler = new Handler(Looper.getMainLooper());
        rv_tv_show.setVisibility(View.GONE);
        tv_no_internet.setVisibility(View.GONE);
        btn_retry.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        getData();

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private void showRetryButton() {
        tv_no_internet.setVisibility(View.VISIBLE);
        btn_retry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        btn_retry.setOnClickListener(view -> {
            tv_no_internet.setVisibility(View.GONE);
            btn_retry.setVisibility(View.GONE);
            getData();
        });
    }

    private void getData() {
        if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            rv_tv_show.setVisibility(View.GONE);
            tv_no_internet.setVisibility(View.GONE);
            btn_retry.setVisibility(View.GONE);

            Call<TvShowResponse> client = ApiConfig.getApiService().getTvShows(ApiConfig.getApiKey());
            client.enqueue(new Callback<TvShowResponse>() {
                @Override
                public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                    if (response.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        rv_tv_show.setVisibility(View.VISIBLE);
                        if (response.body() != null) {
                            tvShows = response.body().getTvShows();
                            TvShowAdapter adapter = new TvShowAdapter(tvShows);
                            rv_tv_show.setAdapter(adapter);
                            rv_tv_show.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        if (response.body() != null) {
                            Log.e("TvShowFragment", "OnFailure: " + response.message());
                        }
                    }
                }

                @Override
                public void onFailure(Call<TvShowResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to load tv shows", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            handler.postDelayed(() -> showRetryButton(), 100);
        }
    }
}