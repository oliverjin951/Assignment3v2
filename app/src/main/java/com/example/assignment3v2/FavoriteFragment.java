package com.example.assignment3v2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Cat> catArrayList = new ArrayList<Cat>();
    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_cat, container, false);
//        recyclerView = view.findViewById(R.id.fav_main);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        FavoriteAdapter favoriteAdapter = new FavoriteAdapter();
//        favoriteAdapter.setData(catArrayList);
//        recyclerView.setAdapter(favoriteAdapter);
        return view;
    }

}
