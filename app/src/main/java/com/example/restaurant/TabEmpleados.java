package com.example.restaurant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabEmpleados extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_empleados);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        if (toolbar != null) {

            setSupportActionBar(toolbar);


        }

    }


    private ArrayList<Fragment> agregarFragments() {


        ArrayList<Fragment> listaFragemnts = new ArrayList<>();
        listaFragemnts.add(new RecyclerViewFragmentEmpleados());
        listaFragemnts.add(new FragmentAdministrationEmpleados());

        return listaFragemnts;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Empleados");
        tabLayout.getTabAt(1).setText("Administrar");


    }
}