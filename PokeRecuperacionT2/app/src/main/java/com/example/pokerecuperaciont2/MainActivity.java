package com.example.pokerecuperaciont2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    private Context context = this;
    static String host = "https://pokeapi.co/api/v2/pokemon?limit=151";


    private TextView pokeTextView;
    private ImageView pokeImageView;
    private String pokeName;
    private String pokeUrl;
    private List<Pokemon> pokemon;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.progressBar = findViewById(R.id.progress_bar);

        getPokemon();
    }


    public void getPokemon() throws NullPointerException {
        progressBar.setVisibility(View.VISIBLE);



        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                host,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray results = null;
                        progressBar.setVisibility(View.INVISIBLE);

                        try {
                            results = response.getJSONArray("results");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject result = null;
                            try {
                                result = results.getJSONObject(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                pokemon.add(new Pokemon(
                                        result.getString("name"),
                                        result.getString("url")));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);

                if (error.networkResponse == null) {

                    Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                } else {

                    int serverCode = error.networkResponse.statusCode;
                    Toast.makeText(MainActivity.this, "Ha habido un error" + serverCode, Toast.LENGTH_SHORT).show();
                }
            }


        }
        );
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
}
