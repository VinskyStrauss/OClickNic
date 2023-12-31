package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class ArztMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arzt_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        try {
            Button list =  view.findViewById(R.id.patientlist);
            //Intent
            Intent intent = new Intent(getActivity(), PatientList.class);
            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
            //Searchview
            SearchView searchBar = view.findViewById(R.id.search_bar);
            Intent intent2 = new Intent(getActivity(), SearchArztActivity.class);
            searchBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent2);
                    searchBar.clearFocus();
                }
            });
            searchBar.setOnQueryTextFocusChangeListener((v, hasFocus)->{
                if(hasFocus)
                startActivity(intent2);
                requireActivity().overridePendingTransition(0,0);
                searchBar.clearFocus();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}