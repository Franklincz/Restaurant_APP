package com.example.restaurant;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Menu_employees extends AppCompatActivity {
    FirebaseAuth mAuth;
    CardView cardCerrarSesion, cardgenerarComanda, cardMesas, cardExtras, cardPlatillo, cardAgregarReceta, cardMesaAsignada;


  /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("do you want to hang out?").setPositiveButton("si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent( Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();

        }

        return super.onKeyDown(keyCode, event);
    }*/


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_employees);

        mAuth = FirebaseAuth.getInstance();


        cardCerrarSesion = (CardView) findViewById(R.id.Cardcerrarsesionempleoye);
        cardgenerarComanda = (CardView) findViewById(R.id.CardGenerarComanda);
        cardMesas = findViewById(R.id.CardMesas1);

        cardExtras = findViewById(R.id.CardExtras);
        cardPlatillo = findViewById(R.id.CardPlatillo);
        cardAgregarReceta = findViewById(R.id.CardAgregarRecet);
        cardMesaAsignada = findViewById(R.id.CardMesaAsignada);

        cardMesas.setOnClickListener(this::onClick);
        cardgenerarComanda.setOnClickListener(this::onClick);
        cardCerrarSesion.setOnClickListener(this::onClick);
        cardAgregarReceta.setOnClickListener(this::onClick);
        cardMesaAsignada.setOnClickListener(this::onClick);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Cardcerrarsesionempleoye:
                mAuth.signOut();
                startActivity(new Intent(Menu_employees.this, LoginInicio.class));
                finish();
                break;
            case R.id.CardGenerarComanda:
                startActivity(new Intent(this, ProductosELEGIR.class));
                break;
            case R.id.CardMesaAsignada:
                startActivity(new Intent(this, RegistrarDatosPedidos.class));
                break;
            case R.id.CardIngredientes:
                startActivity(new Intent(this, ListaIngredientes.class));
                break;
            case R.id.CardPlatillo:
                startActivity(new Intent(this, PlatillosActivity.class));
                break;
            case R.id.CardMesas1:
                startActivity(new Intent(this, ReporteMesas.class));
                break;

        }


    }
}