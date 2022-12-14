package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MrtResultFragment extends Fragment {
    View view;
    ImageButton back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        back.findViewById(R.id.revert);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mrt_result, container, false);
        return view;
    }

}