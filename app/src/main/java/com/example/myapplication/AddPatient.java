package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class AddPatient extends AppCompatActivity {
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        //Declare edit Text and other Object
        EditText nachname = findViewById(R.id.editTextTextPersonName2);
        EditText vorname = findViewById(R.id.editVorname);
        EditText address = findViewById(R.id.editAdresse);
        EditText krankKasse = findViewById(R.id.krankenkassenummer);
        EditText geburtsTag = findViewById(R.id.editbirthDate);
        EditText rufNummer = findViewById(R.id.editNumber);
        EditText ID = findViewById(R.id.editTextTextPersonName);
        EditText sex = findViewById(R.id.editSex);


        add = findViewById(R.id.submit);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set all value
                String nachName = nachname.getText().toString();
                String vorName = vorname.getText().toString();
                String adresse = address.getText().toString();
                int versicherung = Integer.parseInt(krankKasse.getText().toString());
                String birthdate = geburtsTag.getText().toString();
                String phoneNumber = rufNummer.getText().toString();
                int patientID = Integer.parseInt(ID.getText().toString());
                String Sex = sex.getText().toString();
                //Assign data to PatientClass
                PatientClass newPatient;
                newPatient = new PatientClass(patientID,nachName,vorName,adresse,Sex,versicherung,birthdate,phoneNumber,"Krank",0,"",0);
                ContainerAndGlobal.addPatient(newPatient);
            }
        });
    }
}