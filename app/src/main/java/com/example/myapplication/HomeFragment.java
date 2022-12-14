package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        Button arzt  =  view.findViewById(R.id.doctor);
        Button lab =  view.findViewById(R.id.labor);
        Button administration = view.findViewById(R.id.verwalter);
        //Assign Button
        arzt.setOnClickListener(v -> startActivity(new Intent(getActivity(), ArztSearchActivity.class)));
        lab.setOnClickListener(v -> startActivity(new Intent(getActivity(), LaborMenu.class)));
        administration.setOnClickListener(v -> startActivity(new Intent(getActivity(), MainActivity2.class)));
    }
}