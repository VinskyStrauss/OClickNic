package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PatientData extends AppCompatActivity {

    //String
    String updatedStatus;
    //Boolean for Test
    Boolean isMRT = false;
    //Boolean for BloodTest
    Boolean isBloodTest = false;
    PatientClass patient;
    List<String> statusSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_data);
        //Initiliaze the Object
        Button mrtTest = findViewById(R.id.MRT);
        Button bloodWert = findViewById(R.id.Blutwert);
        patient=ContainerAndGlobal.getPatientSearch();
        if(patient.getSeemrt() == 1 && patient.getSeeBlood() == 0){
            Toast.makeText(PatientData.this, " MRT Result availabe", Toast.LENGTH_SHORT).show();
        }else if (patient.getSeemrt() == 0 && patient.getSeeBlood() == 1) {
            Toast.makeText(PatientData.this, " Blood Test Result availabe", Toast.LENGTH_SHORT).show();
        }else if(patient.getSeemrt() == 1 && patient.getSeeBlood() == 1){
            Toast.makeText(PatientData.this, "MRT and Blood Test Result availabe", Toast.LENGTH_SHORT).show();
        }else if (patient.getSeemrt() == 0 && patient.getSeeBlood() == 0){
            Toast.makeText(PatientData.this, "No Test Result availabe", Toast.LENGTH_SHORT).show();
        }
        TextView name = findViewById(R.id.vollname);
        TextView id = findViewById(R.id.ID);
        TextView sex = findViewById(R.id.geschlecht);
        EditText text = findViewById(R.id.editText);
        //Button
        Button save = findViewById(R.id.saveData);
        //CheckBox
        CheckBox mrt = findViewById(R.id.switch1);
        CheckBox blood = findViewById(R.id.switch2);
        //Image View
        ImageView back = findViewById(R.id.imageView2);
        //Array for spinner
        ArrayList<String> state = new ArrayList<>();
        //Add element to array
        state.add("Infected");
        state.add("Stabil");
        state.add("Critical");
        state.add("Healed");
        //Spinner
        Spinner status = findViewById(R.id.spinner);
        //List for spinner
            statusSpinner =  new ArrayList<>();
            statusSpinner.add(0,patient.getStatus());
            addState(state,statusSpinner,patient);
        //Set Spinner
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, statusSpinner);
        //Dropdown
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Attaching data adapter to spinner
        status.setAdapter(dataAdapter);

        //Set Patient Data
        name.setText(patient.getNachname()+", "+patient.getVorname());
        id.setText(Integer.toString(patient.getZimmerNum()));
        sex.setText(patient.getSex());
        text.setText(patient.getBemerkung());
        //Implementation of Button
        mrtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(patient.getSeemrt() == 1){
                    startActivity(new Intent(PatientData.this,MrtResultPatient.class));
                }else{
                    Toast.makeText(PatientData.this,"No MRT Result", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bloodWert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(patient.getBloodValueClass() != null) {
                    ContainerAndGlobal.setTmpPatient(patient);
                    startActivity(new Intent(PatientData.this, BloodTestResultPatient.class));
                }else{
                    Toast.makeText(PatientData.this,"No Blood Test Result", Toast.LENGTH_SHORT).show();
                }
            }
        });

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updatedStatus = statusSpinner.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient.setBemerkung(text.getText().toString());
                patient.setStatus(updatedStatus);
                //Set Mrt
                SetMrt(isMRT,patient);
                //Set Blood Test
                SetBloodTest(isBloodTest,patient);
                if(updatedStatus.equals("Healed")) {
                    ContainerAndGlobal.deletePatientDoctor(patient);
                }
                finish();
                Toast.makeText(PatientData.this ,"Patient Data saved ", Toast.LENGTH_SHORT).show();
            }
        });

        //Implementation of checkbox
        //for MRT
        mrt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    isMRT = true;
                    compoundButton.setText("Sent to MRT");
                }else{
                    isMRT = false;
                    compoundButton.setText("No need MRT");
                }
            }
        });
        //for BloodTest
        blood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    isBloodTest = true;
                    compoundButton.setText("Sent to Blood-Test");
                }else{
                    isBloodTest = false;
                    compoundButton.setText("No need Blood-Test");
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

    /**
     * function to add the state to the spinner
     * @param arrayList
     * @param list
     * @param patient
     */
    public void addState(ArrayList<String> arrayList ,List<String> list ,PatientClass patient) {
        for (int i = 0; i < arrayList.size(); i++) {
        if(check(arrayList.get(i),patient.getStatus()) == true) {
            list.add(arrayList.get(i));
        }
        }
    }

    /**
     * function to assign patient to mrt test
     * @param set
     * @param patient
     */
    public void SetMrt(Boolean set, PatientClass patient){
        //Set Mrt
        if(set == true){
            patient.setMrt(1);
            //add Patient to MRT list
            if(ContainerAndGlobal.checkPatient(patient,ContainerAndGlobal.getMrtPatient())) {
                ContainerAndGlobal.addPatientMRT(patient);
            }
        }else{
            patient.setMrt(0);
        }
    }

    /**
     * function to assign patient to mrt test
     * @param set
     * @param patient
     */
    public void SetBloodTest(Boolean set, PatientClass patient){
        //Set Mrt
        if(set == true){
            patient.setBlood(1);
            //add Patient to MRT list
            if(ContainerAndGlobal.checkPatient(patient,ContainerAndGlobal.getBloodPatient())) {
                ContainerAndGlobal.addPatientBloodTest(patient);
            }
        }else{
            patient.setBlood(0);
        }
    }
    public boolean check (String text, String text2){
        if(text.toLowerCase(Locale.ROOT).equals(text2.toLowerCase(Locale.ROOT))){
            return false;
        }else{
            return true;
        }
    }


}
