package com.example.pokerecuperaciont2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    static String host = "https://pokeapi.co/api/v2/pokemon?limit=151";


    private TextView pokeTextView;
    private ImageView pokeImageView;
    private String pokeName;
    private String pokeUrl;
    private List<Pokemon> pokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPokemon();
    }


    public void getPokemon() throws NullPointerException {


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                host,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray results = null;
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
                Toast.makeText(context, "Problema recibiendo pokemon", Toast.LENGTH_LONG).show();


            }


        }
        );
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
}
