package com.example.finalassignment.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalassignment.Database.DatabaseHelper;
import com.example.finalassignment.Adapter.FavoriteAdapter;
import com.example.finalassignment.MainActivity;
import com.example.finalassignment.R;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private List<Object> favoriteList;
    private FavoriteAdapter favoriteAdapter;
    DatabaseHelper databaseHelper;
    TextView tv_no_favorite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Favorites");

        tv_no_favorite = view.findViewById(R.id.tv_no_favorite);
        tv_no_favorite.setVisibility(View.GONE);

        databaseHelper = new DatabaseHelper(getActivity());
        favoriteList = databaseHelper.getFavorites();

        // Inisialisasi RecyclerView dan Adapter
        RecyclerView recyclerView = view.findViewById(R.id.rv_favorite);
        favoriteAdapter = new FavoriteAdapter(favoriteList);

        // Set layout manager dan adapter pada RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(favoriteAdapter);

        if (favoriteList.isEmpty()) {
            tv_no_favorite.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
