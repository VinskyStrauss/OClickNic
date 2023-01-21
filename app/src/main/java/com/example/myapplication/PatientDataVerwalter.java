package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        TextView zimmer = findViewById(R.id.textView10);
        TextView telephone = findViewById(R.id.textView12);
        TextView note = findViewById(R.id.textView13);
        //Object von Patient
        PatientClass patient;
        patient=ContainerAndGlobal.getPatientListsVerwalter().get(position);
        //Button
        Button entlassen = findViewById(R.id.button3);
        //Back
        ImageView back = findViewById(R.id.imageView2);

        name.setText(patient.getNachname() + " , " + patient.getVorname());
        sex.setText(patient.getSex());
        birthdate.setText(patient.getBirthday());
        address.setText(patient.getAdresse());
        status.setText(patient.getStatus());
        insurance.setText(Integer.toString(patient.getVersicherungsnummer()));
        zimmer.setText(Integer.toString(patient.getZimmerNum()));
        telephone.setText(patient.getRufnummer());
        note.setText(patient.getBemerkung());
        //Button Implementation
        entlassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContainerAndGlobal.checkDischarged(patient)){
                    ContainerAndGlobal.deletePatient(patient);
                    finish();
                    Toast.makeText(PatientDataVerwalter.this ," Patient discharged! ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PatientDataVerwalter.this, " Patient still sick! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Exit
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}