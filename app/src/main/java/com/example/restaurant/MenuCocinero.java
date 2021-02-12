package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MenuCocinero extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView rvLista;
    List<Pedido> listapedidos;
    private ValueEventListener eventListener;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cocinero);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvLista = (RecyclerView) findViewById(R.id.rvLista);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvLista.setLayoutManager(lm);
        rvLista.setHasFixedSize(true);

        mAuth = FirebaseAuth.getInstance();//instanceamos el objeto

        listapedidos = new ArrayList<>();
        /// aqui referencia


        final RVAdapter adapter = new RVAdapter(listapedidos);
        rvLista.setAdapter(adapter);


        db = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child(FirebaseReference.PEDIDOS_REFERENCE);

        eventListener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot datasnapshot) {


                listapedidos.removeAll(listapedidos);
                for (DataSnapshot snapshot1 :
                        datasnapshot.getChildren()) {

                    Pedido ped = new Pedido();
                    ped = snapshot1.getValue(Pedido.class);


                    listapedidos.add(ped);


                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ERROR", error.getMessage());
            }
        };

        db.addValueEventListener(eventListener);


    }

/* @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }



    @Override
    protected void onStart() {
        super.onStart();
        // mAuth.addAuthStateListener(mAuthListener);

        if (mAuth.getCurrentUser() != null) {

            startActivity(new Intent(getApplicationContext(), Main_Administrator.class));
            finish();
        }

    }*/

}