package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PatientDaten extends AppCompatActivity {
    private EditText name, adresse, versicherungNr, geburtsdatum;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_daten);

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

                Intent intent = new Intent(PatientDaten.this, AddPatient.class);
                intent.putExtra("Patientname", name_);
                intent.putExtra("Patientadresse", adresse_);
                intent.putExtra("Patientversicherungsnummer", versicherungNr_);
                intent.putExtra("Patientgeburtsdatum", geb_);
                startActivity(intent);

            }


        });
    }
}