package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;


public class VerwalterMenu extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verwalter_menu, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button list = view.findViewById(R.id.button);
        Button add = view.findViewById(R.id.button2);
        //Intent
        Intent intent = new Intent(getActivity(), PatientListVerwalter.class);
        Intent intent2 = new Intent(getActivity(), AddPatient.class);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });
        //Search Function
        SearchView search = view.findViewById(R.id.search_bar);
        Intent intent3 = new Intent(getActivity(), SearchVerwalterActivity.class);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent3);
                search.clearFocus();
            }
        });
        search.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if(hasFocus)
                startActivity(intent3);
            requireActivity().overridePendingTransition(0, 0);
            search.clearFocus();
        });

    }

}