package com.example.assignment3v2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    String url;

    public CatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cat, container, false);
        final EditText searchBar = view.findViewById(R.id.searchBar);
        Button searchButton = view.findViewById(R.id.searchButton);

        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        url = "https://api.thecatapi.com/v1/breeds?api-key=52d832db-85e1-4b0a-9eef-5b553017258d";
        final CatAdapter catAdapter = new CatAdapter();

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Cat[] catObjectArray = gson.fromJson(response, Cat[].class);
                ArrayList<Cat> catsList = new ArrayList<>(Arrays.asList(catObjectArray));

                catAdapter.setData(catsList);
                recyclerView.setAdapter(catAdapter);
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                String searchedCat = searchBar.getText().toString().toLowerCase();
                if (searchedCat.equals("")) {
                    url = "https://api.thecatapi.com/v1/breeds?api-key=52d832db-85e1-4b0a-9eef-5b553017258d";
                } else {
                    url = "https://api.thecatapi.com/v1/breeds/search?q=" + searchedCat;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] catObjectArray = gson.fromJson(response, Cat[].class);
                        ArrayList<Cat> catsList = new ArrayList<>(Arrays.asList(catObjectArray));
                        catAdapter.setData(catsList);
                        recyclerView.setAdapter(catAdapter);
                    }
                };
                Response.ErrorListener errorListener1 = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("oh no");
                    }
                };
                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, responseListener, errorListener1);
                requestQueue.add(stringRequest1);
            }
        });
        return view;
    }


}
