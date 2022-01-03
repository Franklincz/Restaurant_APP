package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    Context context;
    TextView tvCantProductos;
    Button btnVerCarro;
    List<Producto> listaProductos;
    List<ArayProductos> listaAray;
    List<Producto> carroCompra;
    List<Pedido> listaPedidos;
    List<Producto> carroComprasquesetrae;

    public AdaptadorProductos(Context context, TextView tvCantProductos, Button btnVerCarro, List<Producto> listaProductos,
                              List<Producto> carroCompra, List<Producto> carroComprasquesetrae , List<ArayProductos> lista) {
        this.context = context;
        this.tvCantProductos = tvCantProductos;
        this.btnVerCarro = btnVerCarro;
        this.listaProductos = listaProductos;
        this.listaAray=lista;
        this.carroCompra = carroCompra;
        this.carroComprasquesetrae = carroComprasquesetrae;

    }

    public AdaptadorProductos(Context context, List<Producto> carroCompra) {
        this.context = context;
        this.carroCompra = carroCompra;

    }

    public AdaptadorProductos() {


    }


    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_productos, null, false);
        return new AdaptadorProductos.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {//coloca los elementos osea los carga

        productosViewHolder.tvNomProducto.setText(listaProductos.get(i).getNombre());
        productosViewHolder.tvDescripcion.setText(listaProductos.get(i).getNombre());
        productosViewHolder.tvPrecio.setText("S/ " + listaProductos.get(i).getPrecio());

        Glide.with(context).load(listaProductos.get(i).getImgProduct()).into(productosViewHolder.imagenProducto);


     /*productosViewHolder.cbCarro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (productosViewHolder.cbCarro.isChecked() == true) {

                    if (carroComprasquesetrae.isEmpty() == false) {
                        carroCompra = carroComprasquesetrae;
                    }
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(listaProductos.get(i));
                } else if (productosViewHolder.cbCarro.isChecked() == false) {
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) - 1));
                    carroCompra.remove(listaProductos.get(i));
                }
            }
        });*/
        productosViewHolder.btnAgregarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cantidadEligiendo = Integer.parseInt(tvCantProductos.getText().toString());


                if (cantidadEligiendo >= 0) {
                    tvCantProductos.setText("" + (Integer.parseInt(tvCantProductos.getText().toString().trim()) + 1));
                    carroCompra.add(listaProductos.get(i));
                   // listaAray.add(i,new ArayProductos(carroCompra.get(i), "1"));
                    cantidadEligiendo++;
                }
                listaAray.add(i,new ArayProductos(carroCompra.get(i), "1"));


            }
        });


        btnVerCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CarroCompra.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CarroCompras", (Serializable) carroCompra);
                intent.putExtra("Aray", (Serializable) listaAray);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        //  CheckBox cbCarro;
        Button btnAgregarCarro;
        ImageView imagenProducto;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            //     cbCarro = itemView.findViewById(R.id.cbCarro);
            btnAgregarCarro = itemView.findViewById(R.id.btnAgregarCarro);
            imagenProducto = itemView.findViewById(R.id.imagenProducto);

        }
    }
}