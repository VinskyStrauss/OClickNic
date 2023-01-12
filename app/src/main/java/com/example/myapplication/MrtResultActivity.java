package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MrtResultActivity extends AppCompatActivity {
    Button back;
    Button upload;
    Intent labMenu;
    int pos =  ContainerAndGlobal.getPosition();
    PatientClass patientClass = ContainerAndGlobal.getPatientLists().get(pos);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrt_result);
        //Set Button
        back = findViewById(R.id.revert);
        upload = findViewById(R.id.upload);
        //Set Intent
        labMenu = new Intent(this, MrtTestListe.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(labMenu);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patientClass.setSeeMrt(1);
                finish();


            }
        });
    }
}