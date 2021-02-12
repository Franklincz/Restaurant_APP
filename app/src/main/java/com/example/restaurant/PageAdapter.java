package com.example.restaurant;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listaFragments;

    public PageAdapter(FragmentManager fm , ArrayList<Fragment>listaFragments) {
        super(fm);
        this.listaFragments=listaFragments;
    }


    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }





}
