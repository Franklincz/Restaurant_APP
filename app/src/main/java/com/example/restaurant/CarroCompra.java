package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.List;

public class CarroCompra extends AppCompatActivity {

    List<Producto> carroCompras;
    AdaptadorCarroCompras adaptador;
    ProductosELEGIR p;
    RecyclerView rvListaCarro;
    TextView tvTotal;
    List<Producto> carrocompraseguricomprando;
    Button btconfirmarCompra;
    Button seguircomprando;
    DatabaseReference db3, db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_compra);
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().hide();

        rvListaCarro = findViewById(R.id.rvListaCarro);
        tvTotal = findViewById(R.id.tvTotal);
        btconfirmarCompra = (Button) findViewById(R.id.btxconfirmarPedido);
        seguircomprando = (Button) findViewById(R.id.btxseguircomprando);

        carroCompras = (List<Producto>) getIntent().getSerializableExtra("CarroCompras");
        rvListaCarro.setLayoutManager(new GridLayoutManager(CarroCompra.this, 1));
        db3 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child(FirebaseReference.PEDIDOS_REFERENCE);


        adaptador = new AdaptadorCarroCompras(this, carroCompras, tvTotal, btconfirmarCompra, seguircomprando, db3);
        rvListaCarro.setAdapter(adaptador);

        //   rvListaCarro.setAdapter(adaptador);

        //  Toast.makeText(getApplicationContext(), "key del nodo" + keyPedido(), Toast.LENGTH_SHORT).show();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(this, "el pepeee" + (carroCompras.size() - 1), Toast.LENGTH_SHORT).show();



                adaptador.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {


        Toast.makeText(getApplicationContext(), "mariconcitoooooo", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getApplicationContext(), ProductosELEGIR.class);
        intent.putExtra("CarroComprasXP", (Serializable) carroCompras);
        //                context.startActivity(intent);ejecuta super.onBackPressed() para que finalice el metodo cerrando el activity
        super.onBackPressed();
    }

}