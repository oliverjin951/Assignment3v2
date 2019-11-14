package com.example.assignment3v2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatFragment extends Fragment {
    SearchView searchBar;
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    String url = "https://api.thecatapi.com/v1/breeds?api-key=52d832db-85e1-4b0a-9eef-5b553017258d";

    public CatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cat, container, false);

        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Cat[] catObjectArray = gson.fromJson(response, Cat[].class);
                ArrayList<Cat> catsList = new ArrayList<>(Arrays.asList(catObjectArray));
                final CatAdapter catAdapter = new CatAdapter(catsList);
                catAdapter.setData(catsList);
                recyclerView.setAdapter(catAdapter);

                searchBar = view.findViewById(R.id.searchBar);
                searchCat(searchBar);

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
        return view;
    }

    private void searchCat(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                catAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                catAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
