package com.example.restaurant;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Details_Pedido extends AppCompatActivity {
    public static ArrayList<ArayProductos> p = new ArrayList<>();
    ArrayList<String> listaProductos = new ArrayList<>();
    TextView tvmuestra;
    private DatabaseReference db;
    private Button btnconfirmar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__pedido);


        btnconfirmar = findViewById(R.id.btnconfirmarPedido);
        String datoobtenido = "";
        p = Utilidades.p;
        Bundle dat;

        tvmuestra = findViewById(R.id.tvmuestra);
        dat = getIntent().getExtras();
        if (dat != null) {
            datoobtenido = dat.getString("idpedido");
            tvmuestra.setText(datoobtenido);
        }


// aqui quiero mostrar el campo nombre de mi  elemento seleccionado en el recyclerViw , pero quiero que sea de
// acuerdo  a la posicion del elemento que seleccion  , en este caso solop me va a mostrar


        ListView lvProductos = (ListView) findViewById(R.id.lsvSO);


        for (int i = 0; i < p.size(); i++) {


            listaProductos.add(p.get(i).getP().getNombre());

            ArrayAdapter adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProductos);
            lvProductos.setAdapter(adaptador);
        }


        // aqui voy a amostrar con lo que acabo de implementar


//para confirmar el  pedido , cuando doy click en el boton confirmar , se elimina el pedido y se regresa a la activity anterior
        final String finalDatoobtenido = datoobtenido;
        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmarPedido(finalDatoobtenido);

            }


        });


    }

    public void confirmarPedido(final String finalDatoobtenido) {


        // Referencia nuestra base de datos
        db = (DatabaseReference) FirebaseDatabase.getInstance().getReference();
        Query query = db.child(FirebaseReference.PEDIDOS_REFERENCE).orderByChild("numer_pedido").equalTo(finalDatoobtenido);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            // buscamos el nodo con su respectivo key y lo eliminamos
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key =null;
                for (DataSnapshot data : snapshot.getChildren()) {

                  key = data.getKey();

                     FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference(FirebaseReference.PEDIDOS_REFERENCE).child(key).child(FirebaseReference.ESTADO_PEDIDO_REFERENCE);
                    ref.setValue("TERMINADO");
                         /*
                         *
                         *  Pedido ped = new Pedido();
                            ped= data.getValue(Pedido.class);
                            ped.setEstado("YYYYY");
                         * */

                }

                Toast.makeText(Details_Pedido.this, "el key es"+key, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //para regresar a mi otro activity
        Toast.makeText(getApplicationContext(), "El pedido ha sido confirmado exitosamnte", Toast.LENGTH_LONG).show();
        finish();


    }


}