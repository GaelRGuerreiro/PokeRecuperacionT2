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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    static String host = "https://pokeapi.co/api/v2/pokemon";

    private TextView pokeTextView;
    private ImageView pokeImageView;
    private String pokeName;
    private String pokeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPokemon();
    }


    public void getPokemon() throws NullPointerException {


        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                host+"",
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            pokeName = response.getString("name");
                            pokeImage = response.getString("url");
                        } catch (Exception e) {
                        }
                        pokeTextView = findViewById(R.id.textoPokemon);
                        pokeTextView.setText(pokeName);
                        pokeImageView = findViewById(R.id.imagenCelda);
                        try {
                            Util.downloadBitmapToImageView(response.getString("url"), pokeImageView);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
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
