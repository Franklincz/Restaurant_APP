package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main_Administrator extends AppCompatActivity {
    CardView CardViewPlatillo,CardViewCerrarsesion, CardViewEmpleado, CardViewIngredientes, CardViewAsignarMeseros, CardViewStock, CardViewEstadisticas;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__administrator);
     //   getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();//instanceamos el objeto
        CardViewPlatillo = (CardView) findViewById(R.id.CardPlatillo);
        CardViewEmpleado = (CardView) findViewById(R.id.CardEmpleado);
        CardViewIngredientes = (CardView) findViewById(R.id.CardIngredientes);
        CardViewAsignarMeseros = (CardView) findViewById(R.id.CardAsignarMesero);
        CardViewEstadisticas = (CardView) findViewById(R.id.CardEstadisticas);
        CardViewCerrarsesion = (CardView) findViewById(R.id.CardviewCerrarSesion);


        CardViewPlatillo.setOnClickListener(this::onClick);
        CardViewEmpleado.setOnClickListener(this::onClick);
        CardViewIngredientes.setOnClickListener(this::onClick);
        CardViewAsignarMeseros.setOnClickListener(this::onClick);
        CardViewEstadisticas.setOnClickListener(this::onClick);
        CardViewCerrarsesion.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.CardPlatillo:
                startActivity(new Intent(this, PlatillosActivity.class));
                break;
            case R.id.CardEmpleado:
                startActivity(new Intent(this, TabEmpleados.class));
                break;
            case R.id.CardIngredientes:
                startActivity(new Intent(this,ReporteIngredientes.class));
                break;
            case R.id.CardEstadisticas:
                startActivity(new Intent(this, ReportesGraficas.class));
                break;
            case R.id.CardviewCerrarSesion:
                mAuth.signOut();
                startActivity(new Intent(Main_Administrator.this, LoginInicio.class));
                finish();
                break;
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "mi metodo onback", Toast.LENGTH_SHORT).show();
        this.finish();


    }
}