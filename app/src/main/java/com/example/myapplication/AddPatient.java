package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddPatient extends AppCompatActivity {
    Button add;
    TextView nameTextView, Adresse,VrNr,Geburt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
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


        add = findViewById(R.id.submit);
        Intent intent = new Intent(this ,MainActivity2.class);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
