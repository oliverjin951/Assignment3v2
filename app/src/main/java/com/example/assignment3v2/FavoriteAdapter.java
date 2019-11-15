package com.example.assignment3v2;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CatViewHolder> {
    private List<Cat> catsArrayList;


    public FavoriteAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        CatViewHolder catViewHolder = new FavoriteAdapter.CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cat catObject = catsArrayList.get(position);
        final Context context = holder.view.getContext();
        holder.catName.setText(catObject.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("id", catObject.getId());
                intent.putExtra("fav",false);
                context.startActivity(intent);
            }
        });
    }


    public int getItemCount() {
        return catsArrayList.size();
    }

    public void setData(ArrayList<Cat> data) {
        this.catsArrayList = data;

    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catName;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            catName = itemView.findViewById(R.id.catName);
        }
    }
}


