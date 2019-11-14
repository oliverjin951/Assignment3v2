package com.example.assignment3v2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CatViewHolder> {
    private ArrayList<Cat> catArrayList;
    Context context;
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.context = parent.getContext();
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        public Object view;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
