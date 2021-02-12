package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorCarroCompras extends RecyclerView.Adapter<AdaptadorCarroCompras.ProductosViewHolder> {
    public int cantidadDeProducto = 0;
    Context context;
    List<Producto> carroCompra;
    List<Producto> carroCompraenviar;
    Pedido ListaConfirmadaPedido;
    ArrayList<ArayProductos> ListaConfirmadaProductos = new ArrayList<>();
    TextView tvTotal;
    double total = 0;

    DecimalFormat formato1 = new DecimalFormat("#.000");
    DatabaseReference db;
    List<Producto> listaProductos;
    Producto p;
    Button seguirconmprando, btncomprar;

    public AdaptadorCarroCompras(Context context, List<Producto> carroCompra, TextView tvTotal,
                                 Button btncomprar, Button seguirconmprando, DatabaseReference db) {
        this.context = context;

        this.carroCompra = carroCompra;
        this.db = db;
        this.tvTotal = tvTotal;
        this.btncomprar = btncomprar;
        this.seguirconmprando = seguirconmprando;

        for (int i = 0; i < carroCompra.size(); i++) {

            total = total + Double.valueOf(formato1.format(Double.parseDouble("" + carroCompra.get(i).getPrecio())));

        }
        tvTotal.setText("" + (String.format("%.2f", total)));


    }


    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_carro_compras, null, false);
        return new AdaptadorCarroCompras.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductosViewHolder productosViewHolder, final int i) {

        productosViewHolder.edtcant.setText("" + 1);

        productosViewHolder.tvNomProducto.setText(carroCompra.get(i).getNombre());
        productosViewHolder.tvDescripcion.setText(carroCompra.get(i).getDescripcion());
        productosViewHolder.tvPrecio.setText("Precio unidad :" + carroCompra.get(i).getPrecio());
        productosViewHolder.btnEliminarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //

                total = Double.valueOf(formato1.format((total - (carroCompra.get(i).getPrecio()))));


                carroCompra.remove(i);

                notifyDataSetChanged();

                tvTotal.setText("" + total);


            }

        });


        carroCompraenviar = carroCompra;

        //para confirmar mi compra

        btncomprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaConfirmadaProductos.clear();


                for (int i = 0; i < carroCompraenviar.size(); i++) {
                    ListaConfirmadaProductos.add(new ArayProductos(carroCompraenviar.get(i), "3"));

                }

                ListaConfirmadaPedido = new Pedido("mesa 12", ListaConfirmadaProductos);
                db.push().setValue(ListaConfirmadaPedido);


            }
        });

        seguirconmprando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductosELEGIR.class);
                intent.putExtra("CarroComprasXP", (Serializable) carroCompraenviar);
                context.startActivity(intent);
                Toast.makeText(context, "holaaaaaaa" + carroCompraenviar.size(), Toast.LENGTH_SHORT).show();


            }
        });


        productosViewHolder.btnAumentarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidadDeProducto = 0;
                cantidadDeProducto = cantidadDeProducto + 1;

                productosViewHolder.edtcant.setText("" + cantidadDeProducto);


            }
        });
        productosViewHolder.btnDismiProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cantidadDeProducto = 0;



                cantidadDeProducto = cantidadDeProducto - 1;
                productosViewHolder.edtcant.setText("" + cantidadDeProducto);
            }
        });


    }

    @Override
    public int getItemCount() {
        return carroCompra.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNomProducto, tvDescripcion, tvPrecio;
        ImageButton btnEliminarProd, btnAumentarProd, btnDismiProd;
        EditText edtcant;

        public ProductosViewHolder(View itemView) {
            super(itemView);

            tvNomProducto = itemView.findViewById(R.id.tvNomProducto);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            btnEliminarProd = itemView.findViewById(R.id.btnELiminarProducto);
            btnAumentarProd = itemView.findViewById(R.id.btnMAS);
            btnDismiProd = itemView.findViewById(R.id.btnMENOS);
            edtcant = itemView.findViewById(R.id.edtCant);


        }
    }
}
