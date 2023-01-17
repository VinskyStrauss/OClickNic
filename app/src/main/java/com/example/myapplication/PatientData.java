package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatientData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);
        //Initiliaze the Object
        Button mrtTest = findViewById(R.id.MRT);
        Button bloodWert = findViewById(R.id.Blutwert);
        int position = ContainerAndGlobal.getPosition();
        PatientClass patient;
        patient=ContainerAndGlobal.getPatientLists().get(position);
        TextView name = findViewById(R.id.vollname);
        TextView id = findViewById(R.id.ID);
        TextView sex = findViewById(R.id.geschlecht);
        EditText status = findViewById(R.id.condition);
        EditText text = findViewById(R.id.editText);
        //Button
        Button save = findViewById(R.id.saveData);
        //Switch
        CheckBox mrt = findViewById(R.id.switch1);
        CheckBox blood = findViewById(R.id.switch2);
        //Image View
        ImageView back = findViewById(R.id.imageView2);

        name.setText(patient.getNachname()+", "+patient.getVorname());
        id.setText(Integer.toString(patient.getId()));
        sex.setText(patient.getSex());
        status.setText(patient.getStatus());
        text.setText(patient.getBemerkung());
        //Implementation of Button
        mrtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(patient.getSeemrt() == 1){
                    startActivity(new Intent(PatientData.this,MrtResultPatient.class));
                }
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient.setBemerkung(text.getText().toString());
                patient.setStatus(status.getText().toString());
                finish();
                Toast.makeText(PatientData.this ," Note saved ", Toast.LENGTH_LONG).show();
            }
        });

        //Implementation of switch
        //for MRT
        mrt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    patient.setMrt(1);
                    compoundButton.setText("Sent to MRT");
                    //add Patient to MRT list
                    if(ContainerAndGlobal.checkPatient(patient,ContainerAndGlobal.getMrtPatient())) {
                        ContainerAndGlobal.addPatientMRT(patient);
                    }
                }else{
                    patient.setMrt(0);
                    compoundButton.setText("No need MRT");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.doctor_menu,menu);
        return true;
    }

}
