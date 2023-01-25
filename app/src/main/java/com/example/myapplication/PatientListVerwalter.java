package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PatientListVerwalter extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        show();
    }
    public void show()
    {
        setContentView(R.layout.activity_patient_list_verwalter);
        //Back
        ImageView back = findViewById(R.id.imageView3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list =  findViewById(R.id.list_item);
        ListAdapter listAdapter = new ListAdapter(this, ContainerAndGlobal.getPatientListsVerwalter(), this);
        list.setAdapter(listAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onResume() {
        super.onResume();
        ListAdapter listAdapter = new ListAdapter(this, ContainerAndGlobal.getPatientListsVerwalter(),this);
        list.setAdapter(listAdapter);
    }
    private View.OnClickListener newActivity(int patientPos){
        startActivity(new Intent(PatientListVerwalter.this,PatientDataVerwalter.class));
        return null;
    }

    @Override
    public void onItemClick(int position) {
        ContainerAndGlobal.savePosition(position);
        startActivity(new Intent(PatientListVerwalter.this,PatientDataVerwalter.class));
    }
}
