package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PatientDaten extends AppCompatActivity {
    TextView nameTextView, Adresse,VrNr,Geburt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_daten);

        nameTextView = findViewById(R.id.name);
        Adresse = findViewById(R.id.adresse);
        VrNr = findViewById(R.id.krankenkasse);
        Geburt = findViewById(R.id.birthDate);

        String patientname = getIntent().getStringExtra("Patientname");
        String adresse = getIntent().getStringExtra("Patientadresse");
        String geburtsdatum = getIntent().getStringExtra("Patientgeburtsdatum");
        String Ver = getIntent().getStringExtra("Patientversicherungsnummer");

        nameTextView.setText(patientname);
        Adresse.setText(adresse);
        VrNr.setText(Ver);
        Geburt.setText(geburtsdatum);


    }
}