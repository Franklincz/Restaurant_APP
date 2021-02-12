package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlatillosActivity extends AppCompatActivity {

    EditText EdtnombreProducto;
    EditText EdtprecioProducto;
    Button btnAgregar;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platillos);


        EdtnombreProducto = findViewById(R.id.txtNOmbreProducto);
        EdtprecioProducto = findViewById(R.id.txtprecio);
        btnAgregar = findViewById(R.id.guardarProducto);
        producto = new Producto();
        final DatabaseReference db;

        db = (DatabaseReference) FirebaseDatabase.getInstance().getReference(FirebaseReference.PRODUCTOS_REFERENCE);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String valorid = producto.getId();
                int valorStock = producto.getStock();


                if (producto.getNombre().equalsIgnoreCase(EdtnombreProducto.getText().toString())) {

                    producto.setStock(valorStock + 1);

                   // db.child(id).setValue(producto);

                } else if (producto.getNombre().equalsIgnoreCase(EdtnombreProducto.getText().toString()) == false) {
                    producto.setStock(0);
                    String id =db.push().getKey();
                    int stocknuevoproducto = producto.getStock();
                    producto = new Producto(id, EdtnombreProducto.getText().toString(), Double.parseDouble(EdtprecioProducto.getText().toString()), stocknuevoproducto + 1);

                    db.child( id).setValue(producto);
                }


            }
        });


    }
}