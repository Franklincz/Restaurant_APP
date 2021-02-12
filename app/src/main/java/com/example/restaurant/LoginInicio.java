package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

public class LoginInicio extends AppCompatActivity {
    int tipodeEmpleado = 0;
    String tipoCompara;
    DatabaseReference db;


    private TextInputEditText txtEmail, txtPass;
    Button btnIngresar;
    private Spinner SpinnerUser;
    private String Email = "";
    private String passw = "";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_inicio);
      //  getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();//instanceamos el objeto
        txtEmail = findViewById(R.id.editTextEmail);
        txtPass = findViewById(R.id.editTextPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        SpinnerUser = findViewById(R.id.spinnerUser);
        db = FirebaseDatabase.getInstance().getReference().child(FirebaseReference.USUARIOS_REFERENCE);
        validarTypeUser();


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email = txtEmail.getText().toString();
                passw = txtPass.getText().toString();


                if (!Email.isEmpty() && !passw.isEmpty()) {
                    //   loginUser();

                    consultaTipoUsuario();

                } else {
                    Toast.makeText(LoginInicio.this, "Ingrese datos completos", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {

            //startActivity(new Intent(this,MainActivity.class));
            Toast.makeText(this, "si hay usuarioa" + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();


            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            String tipo_usuariooo = preferences.getString("tipouser", "null");
            if (tipo_usuariooo.equalsIgnoreCase("ADMINISTRATOR")) {
                startActivity(new Intent(getApplicationContext(), Main_Administrator.class));

                finish();
            }
            if (tipo_usuariooo.equalsIgnoreCase("COOK")) {
                startActivity(new Intent(getApplicationContext(), MenuCocinero.class));
                finish();
            }

            if (tipo_usuariooo.equalsIgnoreCase("WAITER")) {
                startActivity(new Intent(getApplicationContext(), Menu_employees.class));

                finish();
            }

        } else {
            Toast.makeText(this, "no hay ningun usuario activo", Toast.LENGTH_SHORT).show();
        }
    }


    private void validarTypeUser() {

        SpinnerUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(LoginInicio.this, "selecciono" + parent.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                tipodeEmpleado = parent.getSelectedItemPosition();
                tipoCompara = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }


    private void loginUser(String dato) {
        mAuth.signInWithEmailAndPassword(Email, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                    SharedPreferences preferences = getSharedPreferences("credenciales", MODE_PRIVATE);
                    String SHARED_PREFERENCE_TIPO_USER = dato;

                    SharedPreferences.Editor editor = preferences.edit();
                    //almaceno los datos
                    editor.putString("tipouser", SHARED_PREFERENCE_TIPO_USER);
                    editor.commit();


                    Toast.makeText(LoginInicio.this, "mi datazooo" + dato, Toast.LENGTH_SHORT).show();

                    switch (tipodeEmpleado) {
                        case 1:
                            startActivity(new Intent(getApplicationContext(), Main_Administrator.class));

                            Toast.makeText(getApplicationContext(), "entro a la actividad 1" + tipodeEmpleado, Toast.LENGTH_SHORT).show();

                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "entro a la actividad 2" + tipodeEmpleado, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MenuCocinero.class));
                            break;
                        case 3:
                            startActivity(new Intent(getApplicationContext(), Menu_employees.class));
                            Toast.makeText(getApplicationContext(), "entro a la actividad 3" + tipodeEmpleado, Toast.LENGTH_SHORT).show();
                            break;

                    }


                } else {
                    Toast.makeText(LoginInicio.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void consultaTipoUsuario() {
        if (tipodeEmpleado > 0) {
            db.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int cant = 0;


                    for (DataSnapshot snapshot1 :
                            snapshot.getChildren()) {
                        Empleado ped;
                        ped = snapshot1.getValue(Empleado.class);

                        try {

                            if (ped.getGmail().equalsIgnoreCase(Email) && ped.getTipoEmpleado().equals(tipoCompara) && ped.getPassword().equals(passw)) {
                                Toast.makeText(getApplicationContext(), "valor de tipo compara" + tipoCompara, Toast.LENGTH_SHORT).show();

                                Toast.makeText(getApplicationContext(), "imprime tipito" + ped.getTipoEmpleado(), Toast.LENGTH_SHORT).show();
                                String datoobtenido = ped.getTipoEmpleado();
//si el usuario es realmente del tipo que el elige en el spinner entonces se ejecuta este metodo
                                loginUser(datoobtenido);
                                break;

                            }

                        } catch (NullPointerException exception) {

                            Toast.makeText(LoginInicio.this, "No hay datos", Toast.LENGTH_SHORT).show();

                        }


                        cant++;
                        Toast.makeText(LoginInicio.this, "cantidaddd" + cant, Toast.LENGTH_SHORT).show();


                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            Toast.makeText(this, "eije usuario", Toast.LENGTH_SHORT).show();
        }


    }


}
