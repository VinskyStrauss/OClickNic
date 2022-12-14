package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class AddPatient extends AppCompatActivity {
    Button add;
    private EditText name, adresse, versicherungNr,geburtsdatum;
    private Button Submit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);

        Submit = findViewById(R.id.submit);
        name = findViewById(R.id.editTextTextPersonName2);
        adresse = findViewById(R.id.editAdresse);
        versicherungNr = findViewById(R.id.krankenkassenummer);
        geburtsdatum = findViewById(R.id.editbirthDate);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_ = name.getText().toString();
                String adresse_ = adresse.getText().toString();
                String versicherungNr_ = versicherungNr.getText().toString();
                String geb_ = geburtsdatum.getText().toString();

                Intent intent = new Intent(AddPatient.this,PatientDaten.class);
                intent.putExtra("Patientname",name_);
                intent.putExtra("Patientadresse",adresse_);
                intent.putExtra("Patientversicherungsnummer",versicherungNr_);
                intent.putExtra("Patientgeburtsdatum",geb_);
                startActivity(intent);

            }
        });






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