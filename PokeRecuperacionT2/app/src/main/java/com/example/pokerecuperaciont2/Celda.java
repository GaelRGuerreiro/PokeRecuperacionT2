package com.example.pokerecuperaciont2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class Celda extends RecyclerView.ViewHolder {

    private TextView name;
    private ImageView image;
    public Celda(@NonNull View itemView) {

        super(itemView);
        name=itemView.findViewById(R.id.textoPokemon);
        image=itemView.findViewById(R.id.imagenCelda);
    }

    public void pintarTextView(Pokemon celda){

        name.setText(celda.getName());
        Util.downloadBitmapToImageView(celda.getImageUrl(), this.image);



    }
}

