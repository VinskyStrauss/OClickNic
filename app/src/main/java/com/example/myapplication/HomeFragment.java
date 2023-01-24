package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Declare Button
        ImageView arzt  =  view.findViewById(R.id.doctor);
        ImageView lab =  view.findViewById(R.id.labor);
        ImageView administration = view.findViewById(R.id.verwalter);
        //make Fragment
        final ArztMenu arztFragment = new ArztMenu();
        final LaborMenu laborMenu = new LaborMenu();
        final VerwalterMenu verwalterMenu = new VerwalterMenu();
        //Assign Button
        arzt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(arztFragment);
            }
        });
        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(laborMenu);
            }
        });
        administration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(verwalterMenu);
            }
        });
    }
}