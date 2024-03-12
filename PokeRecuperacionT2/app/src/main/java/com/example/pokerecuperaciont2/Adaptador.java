package com.example.pokerecuperaciont2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Celda> {

   private List<Pokemon> pokemons;


   public Adaptador(List<Pokemon> datos,Activity activity){
       this.pokemons=datos;
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
        Pokemon datos = pokemons.get(position);
        holder.pintarTextView(datos);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick (View v){
            Context context = v.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("url", datos.getImageUrl());
            context.startActivity(intent);

        }
    });

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
}
