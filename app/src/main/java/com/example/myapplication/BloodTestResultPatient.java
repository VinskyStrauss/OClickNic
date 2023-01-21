package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BloodTestResultPatient extends AppCompatActivity {
    //Patient
    PatientClass patientClass;
    //Declare Variable
    BloodValueClass bloodValueClass;
    TextView date;
    TextView leukozyten;
    TextView lymphozytenPer;
    TextView lymphozytenAbs;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_test_patient);
        //Assign Textview ID
        date = findViewById(R.id.dateText);
        leukozyten = findViewById(R.id.leukozytenText);
        lymphozytenPer = findViewById(R.id.lymphozytenPercentText);
        lymphozytenAbs = findViewById(R.id.lymphozytenAbsolutText);
        back = findViewById(R.id.back);
        //Patient
        patientClass = ContainerAndGlobal.getTmpPatient();
        //Blood Value
        bloodValueClass = patientClass.getBloodValueClass();
        //Set Textview text
        date.setText(bloodValueClass.getDatum());
        leukozyten.setText(String.valueOf(bloodValueClass.getLeukozyten()));
        lymphozytenPer.setText(String.valueOf(bloodValueClass.getLymphozytenPercent()));
        lymphozytenAbs.setText(String.valueOf(bloodValueClass.getLymphozytenAbsolut()));

        //Back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}