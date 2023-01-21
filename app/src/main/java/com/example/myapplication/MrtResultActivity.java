package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MrtResultActivity extends AppCompatActivity {
    Button back;
    Button upload;
    Intent labMenu;
    int pos =  ContainerAndGlobal.getPosition();
    PatientClass patientClass = ContainerAndGlobal.getMrtPatient().get(pos);
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
                finish();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContainerAndGlobal.deleteFromMrt(patientClass);
                patientClass.setSeeMrt(1);
                patientClass.setMrt(0);
                finish();
                Toast.makeText(MrtResultActivity.this ,"Result uploaded to the Doctor ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}