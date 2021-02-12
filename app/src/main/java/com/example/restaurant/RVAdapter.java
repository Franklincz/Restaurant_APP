package com.example.restaurant;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<PedidoViewHolder> {
    List<Pedido> pedidos;

    RVAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //rellenar la vista del recyclerview con cualquier elemento que le pongams
    //doonde tiene qe ir a buscar el elemtno para rellenar la vista
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent,
                false);

        PedidoViewHolder pvh = new PedidoViewHolder(vista);
        return pvh;
    }


    // coge la vista -item y se la pasa a  cada uno de los elementos del recycler
    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {


        holder.txtNropedido.setText("Order ID : " + pedidos.get(position).getNumer_pedido());
        holder.txtMesa.setText(pedidos.get(position).getMesa());
        holder.txtdescrip.setText(pedidos.get(position).getDescripcion());
        holder.txtFecha.setText(pedidos.get(position).getFecha());
        holder.txtHora.setText(pedidos.get(position).getEstado());

        //  holder.imgFoto.setImageResource(personas.get(position).getFotoID());


        //llamamos metodo para mostrar el activity
        holder.setOnclickListeners(pedidos.get(position).getDescripcion(), pedidos.get(position).getListadeProductos(), pedidos.get(position).numer_pedido);

        if(holder.txtHora.getText().equals("TERMINADO")){
            holder.txtHora.setTextColor(Color.WHITE);
            holder.txtHora.setBackgroundColor(Color.BLUE);
            Log.i("VVVV","papapappa");
        }else if(holder.txtHora.getText().equals("EN PROCESO")){
            holder.txtHora.setTextColor(Color.RED);
        }


    }

    //devolver la cantidad de elementos que hay en el recycler view
    @Override








    public int getItemCount() {
        return pedidos.size();

    }

}