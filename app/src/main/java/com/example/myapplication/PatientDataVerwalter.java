package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PatientDataVerwalter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data_verwalter);
        int position = ContainerAndGlobal.getPosition();
        TextView name = findViewById(R.id.textView4);
        TextView sex = findViewById(R.id.textView5);
        TextView birthdate = findViewById(R.id.textView6);
        TextView address= findViewById(R.id.textView7);
        TextView status = findViewById(R.id.textView8);
        TextView insurance = findViewById(R.id.textView9);
        //Object von Patient
        PatientClass patient;
        patient=ContainerAndGlobal.getPatientListsVerwalter().get(position);

        name.setText(patient.getNachname() + " , " + patient.getVorname());
        sex.setText(patient.getSex());
        birthdate.setText(patient.getBirthday().toString());
        address.setText(patient.getAdresse());
        status.setText(patient.getStatus());
        insurance.setText(Integer.toString(patient.getVersicherungsnummer()));


    }
}