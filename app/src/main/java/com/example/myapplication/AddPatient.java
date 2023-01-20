package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPatient extends AppCompatActivity {
    //Button
    Button add;
    ImageView back;
    //Edit Text
    EditText nachname;
    EditText vorname;
    EditText address;
    EditText krankKasse;
    EditText geburtsTag;
    EditText rufNummer;
    EditText zimmerNummerEditText;
    EditText sex;
    //Array List
    ArrayList<EditText> elements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        //Declare edit Text and other Object
        nachname = findViewById(R.id.editTextTextPersonName2);
        vorname = findViewById(R.id.editVorname);
        address = findViewById(R.id.editAdresse);
        krankKasse = findViewById(R.id.krankenkassenummer);
        geburtsTag = findViewById(R.id.editbirthDate);
        rufNummer = findViewById(R.id.editNumber);
        zimmerNummerEditText = findViewById(R.id.editTextTextPersonName);
        sex = findViewById(R.id.editSex);
        //Push editText to array
        elements.add(nachname);
        elements.add(vorname);
        elements.add(address);
        elements.add(krankKasse);
        elements.add(geburtsTag);
        elements.add(rufNummer);
        elements.add(zimmerNummerEditText);
        elements.add(sex);
        //Button
        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        add = findViewById(R.id.submit);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContainerAndGlobal.isFormContainsEmptyElement(elements)) {
                    Toast.makeText(AddPatient.this, " Please fill the data", Toast.LENGTH_LONG).show();
                } else if (ContainerAndGlobal.isDataDuplicate(elements)) {
                    Toast.makeText(AddPatient.this, " Please check the data again", Toast.LENGTH_LONG).show();
                } else {
                    //Set all value
                    String nachName = nachname.getText().toString();
                    String vorName = vorname.getText().toString();
                    String adresse = address.getText().toString();
                    int versicherung = Integer.parseInt(krankKasse.getText().toString());
                    String birthdate = geburtsTag.getText().toString();
                    String phoneNumber = rufNummer.getText().toString();
                    int zimmerNummer = Integer.parseInt(zimmerNummerEditText.getText().toString());
                    String Sex = sex.getText().toString();
                    //Assign data to PatientClass
                    PatientClass newPatient;
                    newPatient = new PatientClass(zimmerNummer, nachName, vorName, adresse, Sex, versicherung, birthdate, phoneNumber, "Krank", 0, "", 0);
                    ContainerAndGlobal.addPatient(newPatient);
                    finish();
                    Toast.makeText(AddPatient.this, " Patient added successfully", Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    public boolean checkData(PatientClass patient) {
        boolean test = false;
        for (int i = 0; i < ContainerAndGlobal.getPatientListsVerwalter().size(); i++) {
            test = checkVorname(patient);
            test = checkNachname(patient);
            test = checkAdress(patient);
            test = checkInsurance(patient, ContainerAndGlobal.getPatientListsVerwalter().get(i));
            test = checkPhone(patient, ContainerAndGlobal.getPatientListsVerwalter().get(i));
            test = checkBed(patient, ContainerAndGlobal.getPatientListsVerwalter().get(i));
        }
        return test;
    }

    public boolean checkVorname(PatientClass newPatient) {
        if (newPatient.getVorname().equals("")) {
            vorname.setError("Cannot be blank");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkNachname(PatientClass newPatient) {
        if (newPatient.getNachname().equals("")) {
            nachname.setError("Cannot be blank");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkAdress(PatientClass newPatient) {
        if (newPatient.getAdresse().equals("")) {
            address.setError("Cannot be blank");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkInsurance(PatientClass newPatient, PatientClass patient) {
        if (newPatient.getVersicherungsnummer() == patient.getVersicherungsnummer()) {
            krankKasse.setError("Insurance number already exist");
            return false;
        } else if (newPatient.getVersicherungsnummer() == 0) {
            krankKasse.setError("Cannot be blank");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkPhone(PatientClass newPatient, PatientClass patient) {
        if (newPatient.getRufnummer().equals(patient.getRufnummer())) {
            rufNummer.setError("Phone number already exist");
            return false;
        } else if (newPatient.getRufnummer().equals("")) {
            rufNummer.setError("Cannot be blank");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkBed(PatientClass newPatient, PatientClass patient) {
        if (newPatient.getZimmerNum() == patient.getZimmerNum()) {
            zimmerNummerEditText.setError("Bed already occupied");
            return false;
        } else if (newPatient.getZimmerNum() == 0) {
            zimmerNummerEditText.setError("Cannot be blank");
            return false;
        } else {
            return true;
        }
    }
}