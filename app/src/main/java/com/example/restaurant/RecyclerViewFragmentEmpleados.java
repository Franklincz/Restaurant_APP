package com.example.restaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RecyclerViewFragmentEmpleados extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerViewEmpleados;
    ArrayList<Empleado> listaEmpleados;
    EmpleadoAdapter empleadoAdapter;
    private DatabaseReference db2;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerViewFragmentEmpleados() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RecyclerViewFragmentEmpleados newInstance(String param1, String param2) {
        RecyclerViewFragmentEmpleados fragment = new RecyclerViewFragmentEmpleados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_recycler_view_empleados, container, false);
        listaEmpleados = new ArrayList<>();
        db2 = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child(FirebaseReference.USUARIOS_REFERENCE);

        recyclerViewEmpleados = (RecyclerView) vista.findViewById(R.id.recyclerviewEmpleados);
        recyclerViewEmpleados.setLayoutManager(new LinearLayoutManager(getContext()));

        lenarLista();
        empleadoAdapter = new EmpleadoAdapter(getContext(),listaEmpleados);
        recyclerViewEmpleados.setAdapter(empleadoAdapter);
        return vista;
    }

    private void lenarLista() {
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaEmpleados.removeAll(listaEmpleados);
                for (DataSnapshot snapshot1 :
                        dataSnapshot.getChildren()) {

                    Empleado ped = new Empleado();
                    ped = snapshot1.getValue(Empleado.class);
                    listaEmpleados.add(ped);


                }

                empleadoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}