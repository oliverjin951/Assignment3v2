package com.example.assignment3v2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import com.example.assignment3v2.CatDetailActivity;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    ArrayList<Cat> catsArrayList;
    ArrayList<Cat> filteredSearchCats;

    public CatAdapter(ArrayList<Cat> catsArrayList) {
        this.catsArrayList = catsArrayList;
        this.filteredSearchCats = new ArrayList<>();
    }


    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);

        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }


    public void onBindViewHolder(@NonNull final CatViewHolder holder, int position) {
        if (filteredSearchCats.isEmpty()) {
            filteredSearchCats = catsArrayList;
        }
        final Cat catObject = catsArrayList.get(position);
        final Context context = holder.view.getContext();
        holder.catName.setText(catObject.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("id", catObject.getId());
                context.startActivity(intent);
            }
        });
    }


    public int getItemCount() {
        if (filteredSearchCats.isEmpty()) {
            filteredSearchCats = catsArrayList;

            return catsArrayList.size();
        } else {
            return filteredSearchCats.size();
        }
    }

    public void setData(ArrayList<Cat> data) {
        this.catsArrayList = data;
        System.out.println(catsArrayList.get(1).getId());

    }

    public Filter getFilter() {
        return new Filter() {
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if (search.isEmpty()) {
                    filteredSearchCats = catsArrayList;
                } else {
                    ArrayList<Cat> catFilter = new ArrayList<>();
                    for (Cat cat : catsArrayList) {
                        if (cat.getName().toLowerCase().contains(search)) {
                            catFilter.add(cat);
                        }
                    }
                    filteredSearchCats = catFilter;
                } FilterResults filterResults = new FilterResults();
                filterResults.values = filteredSearchCats;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                catsArrayList = (ArrayList<Cat>) results.values;
                notifyDataSetChanged();
            }
        };


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



