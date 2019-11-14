package com.example.assignment3v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CatDetailActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String CatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        Intent intent = getIntent();
        CatId = intent.getStringExtra("id");
        final ConstraintLayout activity_cat_detail = findViewById(R.id.activity_cat_detail);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https:api.thecatapi.com/v1/images/search?breed_id=" + CatId;

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatPic[] catPics = gson.fromJson(response, CatPic[].class);
                ArrayList<CatPic> catPicArrayList = new ArrayList<CatPic>(Arrays.asList(catPics));
                CatPic catPicObject = catPicArrayList.get(0);

                Cat[] catArrayObject = catPicObject.getBreeds();
                ArrayList<Cat> catArrayListObject = new ArrayList<Cat>(Arrays.asList(catArrayObject));
                Cat catObject = catArrayListObject.get(0);
                weight weightArrayObject = catObject.getWeight();

                TextView catName12 = activity_cat_detail.findViewById(R.id.nameDetail1);
                TextView catDesc = activity_cat_detail.findViewById(R.id.descDetail);
                ImageView catPic = activity_cat_detail.findViewById(R.id.picDetail);
                TextView catOrigin = activity_cat_detail.findViewById(R.id.originDetail);
                TextView weight = activity_cat_detail.findViewById(R.id.weightDetail);
                TextView catTemp = activity_cat_detail.findViewById(R.id.tempDetail);
                TextView catLife = activity_cat_detail.findViewById(R.id.lifeDetail);
                TextView wikiUrl = activity_cat_detail.findViewById(R.id.wikiLink);
                TextView dogLevel = activity_cat_detail.findViewById(R.id.dogDetail);

                catName12.setText(catObject.getName());
                catDesc.setText("Description: " +catObject.getDescription());
                Glide.with(getApplicationContext()).load(catPicObject.getUrl()).into(catPic);
                catOrigin.setText("Cat Origin Country: " +catObject.getOrigin());
                weight.setText("Weight " +weightArrayObject.getMetric() + " kg");
                catTemp.setText("Temperament: " +catObject.getTemperament());
                catLife.setText("Life Span: " +catObject.getLife_span() + "(years)");
                wikiUrl.setText(catObject.getWikipedia_url());
                dogLevel.setText("Dog Friendly Level " +(catObject.getDog_friendly()) + " / 5");
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

    }
}
