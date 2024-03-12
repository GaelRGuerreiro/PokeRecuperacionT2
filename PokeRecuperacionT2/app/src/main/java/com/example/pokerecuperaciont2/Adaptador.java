package com.example.pokerecuperaciont2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Celda> {

   private List<Celda> pokemons;


   public Adaptador(List<Celda> pokemons){
       this.pokemons=pokemons;
   }


    @NonNull
    @Override
    public Celda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda,parent,false);

        Celda celda = new Celda(view);
        return celda;


    }

    @Override
    public void onBindViewHolder(@NonNull Celda holder, int position) {
        Pokemon pokemon= pokemons.getPosition();
        holder.pintarTextView(pokemons);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
