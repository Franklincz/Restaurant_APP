package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GestionarEmpleado extends AppCompatActivity {

    EditText edtNombre, edtAperlldio, edtDepartamento, edtPassword, edtDni, edtProvincia, edtDistrito, edtDireccion, edtCorreo, edtestado;
    Spinner edttipoUser;
    List<Empleado> listaEmpleado = new ArrayList<>();
    DatabaseReference db;
    String tipoUser;
    Button btnGuardar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_empleado);
        getSupportActionBar().hide();


        edtNombre = findViewById(R.id.edtpassword);
        edtAperlldio = findViewById(R.id.edtApellidos);
        edtDistrito = findViewById(R.id.edtDistrito);
        edtProvincia = findViewById(R.id.edtprovincia);
        btnGuardar = findViewById(R.id.btnAgregarUsuario);
        edtDni = findViewById(R.id.edtdni);
        edtPassword = findViewById(R.id.edtpassword);
        edttipoUser = (Spinner) findViewById(R.id.spinnertipousuario);
        edtCorreo = findViewById(R.id.edtgmail);
        edtestado = findViewById(R.id.edtestado);
        db = (DatabaseReference) FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        edttipoUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoUser = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                agregarEmpleado();
                // LimpiaCampos();
            }
        });


    }


    public void agregarEmpleado() {

        String nombre = edtNombre.getText().toString();
        String apellido = edtAperlldio.getText().toString();
        String direccion = edtProvincia.getText().toString() + "-" + edtDistrito.getText().toString();

        String correo;
        String dni = edtDni.getText().toString();
        String pass = edtPassword.getText().toString();
        String estado = edtestado.getText().toString();


        correo = edtCorreo.getText().toString();
        mAuth.createUserWithEmailAndPassword(edtCorreo.getText().toString(), edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Empleado empleado = new Empleado(nombre, apellido, dni, direccion, tipoUser, estado, correo, pass);

                    String id = mAuth.getCurrentUser().getUid();
                    db.child(FirebaseReference.USUARIOS_REFERENCE).child(id).setValue(empleado).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {

                            if (task2.isSuccessful()) {
                                startActivity(new Intent(GestionarEmpleado.this, Main_Administrator.class));
                                finish();
                            }

                        }
                    });


                } else {
                    Toast.makeText(GestionarEmpleado.this, "no se pudo regitrar ", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


    public void LimpiaCampos() {

        edtNombre.setText("");
        edtAperlldio.setText("");
        edtCorreo.setText("");
        edtCorreo.setText("");
        edtPassword.setText("");


    }


}