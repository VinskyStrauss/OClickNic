package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                    Toast.makeText(AddPatient.this, " Please fill the data", Toast.LENGTH_SHORT).show();
                } else if (ContainerAndGlobal.isDataDuplicate(elements)) {
                    Toast.makeText(AddPatient.this, " Please check the data again", Toast.LENGTH_SHORT).show();
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
                    newPatient = new PatientClass(zimmerNummer, nachName, vorName, adresse, Sex, versicherung, birthdate, phoneNumber, "Stabil", 0, "", 0,0,0);
                    ContainerAndGlobal.addPatient(newPatient);
                    finish();
                    Toast.makeText(AddPatient.this, " Patient added successfully", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}