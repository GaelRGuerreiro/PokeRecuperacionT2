package com.example.pokerecuperaciont2;

import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {
    
    private String name;
    private String image_url;
    
    
    
    
    public String getName(){
        return name;
    }
    
    public String getImageUrl(){
        return image_url;
    }
    
    public Pokemon(String name,String image_url){
        this.name=name;
        this.image_url=image_url;
    }
    
    
    public Pokemon(JSONObject json) throws JSONException {
        this.name=json.getString("name");
        this.image_url=json.getString("image_url");
    }
    
}
