package com.example.restaurant;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.EmpleadoViewHolder> {
    private List<Empleado> items;
    Context context;

    public EmpleadoAdapter(Context context, List<Empleado> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EmpleadoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new EmpleadoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EmpleadoViewHolder viewHolder, int i) {
        //viewHolder);
        viewHolder.nombre.setText("Nombres:" + items.get(i).getNombres());
        viewHolder.dni.setText("DNI:" + items.get(i).getDni());
        viewHolder.labor.setText("Labor:" + items.get(i).getTipoEmpleado());
        viewHolder.estado.setText("Estado:" + items.get(i).getEstado());


        Glide.with(context).load(items.get(i).getImg()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                viewHolder.progressBar.setVisibility(View.GONE);
                viewHolder.imagen.setVisibility(View.VISIBLE);
                viewHolder.imagen.setImageResource(R.drawable.error);

                return false;

            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                viewHolder.progressBar.setVisibility(View.GONE);
                viewHolder.imagen.setVisibility(View.VISIBLE);

                return false;
            }
        }).into(viewHolder.imagen);


    }

    public static class EmpleadoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView dni, nombre, labor, estado;
        public ImageView imagen;
        ProgressBar progressBar;


        public EmpleadoViewHolder(View v) {
            super(v);
            dni = (TextView) v.findViewById(R.id.id);
            nombre = (TextView) v.findViewById(R.id.nombre);
            labor = (TextView) v.findViewById(R.id.apellidos);
            estado = (TextView) v.findViewById(R.id.estado);
            imagen = (ImageView) v.findViewById(R.id.imagen);
            progressBar=(ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public void filterList(List<Empleado> filteritems, List<Empleado> listaoriginal) {
        this.items = filteritems;
        notifyDataSetChanged();


    }


}




