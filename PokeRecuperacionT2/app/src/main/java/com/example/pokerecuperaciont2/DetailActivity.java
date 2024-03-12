package com.example.pokerecuperaciont2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.widget.ProgressBar;


import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private String url;
    private ProgressBar progressBar;

    static String host = "https://pokeapi.co/api/v2/pokemon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        this.progressBar = findViewById(R.id.progress_bar);

        url = getIntent().getStringExtra("url");

        getDatos();
    }


    public void getDatos() throws NullPointerException {
        progressBar.setVisibility(View.VISIBLE);



        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        try {

                        } catch (Exception e) {
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);


            }


        }
        );
        RequestQueue queue = Volley.newRequestQueue(DetailActivity.this);
        queue.add(request);
    }
}
