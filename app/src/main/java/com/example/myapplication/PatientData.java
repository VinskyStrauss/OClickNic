package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatientData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);
        //Initiliaze the Object
        int position = ContainerAndGlobal.getPosition();
        PatientClass patient;
        patient=ContainerAndGlobal.getPatientLists().get(position);
        TextView name = findViewById(R.id.vollname);
        TextView id = findViewById(R.id.ID);
        TextView sex = findViewById(R.id.geschlecht);
        TextView status = findViewById(R.id.condition);
        EditText text = findViewById(R.id.editText);
        //Button
        Button save = findViewById(R.id.saveData);

        name.setText(patient.getNachname()+", "+patient.getVorname());
        id.setText(Integer.toString(patient.getId()));
        sex.setText(patient.getSex());
        status.setText(patient.getStatus());
        text.setText(patient.getBemerkung());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient.setBemerkung(text.getText().toString());
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
