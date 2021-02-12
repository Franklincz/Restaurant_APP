package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductosELEGIR extends AppCompatActivity {

    TextView tvCantProductos;
    Button btnVerCarro;
    RecyclerView rvListaProductos;
    AdaptadorProductos adaptador;
    List<Producto> listaProductos;
    List<Producto> carroCompras = new ArrayList<>();
    List<Producto> carroComprasquesetrae;
    private DatabaseReference db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_e_l_e_g_i_r);

        tvCantProductos = findViewById(R.id.tvCantProductos);
        btnVerCarro = findViewById(R.id.btnVerCarro);
        rvListaProductos = findViewById(R.id.rvListaProductos);
        rvListaProductos.setLayoutManager(new GridLayoutManager(ProductosELEGIR.this, 2));
        carroComprasquesetrae = new ArrayList<>();
        db2 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child(FirebaseReference.PRODUCTOS_REFERENCE);

        //   Log.i("AQUIIII","putitasss"+carroComprasquesetrae.size());
        tvCantProductos.setText("" + carroComprasquesetrae.size());

        /*     listaProductos.add(new Producto(1, "gaseosa", 10.50, "inka kola gaseosa", 12));
        listaProductos.add(new Producto(2, "arroz con pollo", 17.50, "Pollo + papas fritas", 12));
        listaProductos.add(new Producto(3, "pollo al horno", 2.50, "descripcion de pollo al horno", 12));
        listaProductos.add(new Producto(4, "alitas", 15.30, "alitas crocantes", 12));*/
        listaProductos=new ArrayList<>();





        carroComprasquesetrae = (List<Producto>) getIntent().getSerializableExtra("CarroComprasXP");
        if (carroComprasquesetrae == null) {
            Toast.makeText(this, "Aun", Toast.LENGTH_SHORT).show();
        } else if (carroComprasquesetrae != null) {

            carroCompras = carroComprasquesetrae;
            Toast.makeText(this, "carro compras tiene mas valores" + carroComprasquesetrae.size(), Toast.LENGTH_SHORT).show();
            tvCantProductos.setText("" + carroComprasquesetrae.size());
        }
        obtenerProducts();

        adaptador = new AdaptadorProductos(getApplicationContext(), tvCantProductos, btnVerCarro, listaProductos, carroCompras, carroComprasquesetrae);
        rvListaProductos.setAdapter(adaptador);





    }


    public void obtenerProducts() {


        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaProductos.removeAll(listaProductos);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {

                    Producto producto = new Producto();
                   producto= snapshot.getValue(Producto.class);
                    listaProductos.add(producto);


                }

                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


































}