package com.example.restaurant;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MesaViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private ImageButton imgMesa;
    private TextView tvMesa;
    private MesaRecyclerAdapter mesaRecyclerAdapter;

    public MesaViewHolder(View view){
        super(view);
        view.setOnLongClickListener(this);
        imgMesa = (ImageButton) view.findViewById(R.id.imgMesa);
        tvMesa = (TextView) view.findViewById(R.id.tvMesa);

    }

    public ImageButton getImgMes() {return imgMesa; }
    public TextView getTvMesa() {return tvMesa; }

    @Override
    public boolean onLongClick(View v) {
        mesaRecyclerAdapter.clickListener.onItemLongClick(getAdapterPosition(), v);
        return true;
    }
}
