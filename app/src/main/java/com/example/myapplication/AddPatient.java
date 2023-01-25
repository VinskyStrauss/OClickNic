package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPatient extends AppCompatActivity {
    //Button
    Button add;
    Button geburtsTag;
    ImageView back;
    //Edit Text
    EditText nachname;
    EditText vorname;
    EditText address;
    EditText krankKasse;
    EditText rufNummer;
    EditText zimmerNummerEditText;
    Spinner sex;
    //String for geschlecht
    String sexString;
    String date;
    //Array List
    ArrayList<EditText> elements = new ArrayList<>();
    List<String> sexSpinner = null;
    //Date Picker
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        try {
            //init datePicker
            initDatePicker();
            //Declare edit Text and other Object
            nachname = findViewById(R.id.editTextTextPersonName2);
            vorname = findViewById(R.id.editVorname);
            address = findViewById(R.id.editAdresse);
            krankKasse = findViewById(R.id.krankenkassenummer);
            rufNummer = findViewById(R.id.editNumber);
            zimmerNummerEditText = findViewById(R.id.editTextTextPersonName);
            sex = findViewById(R.id.editSex);
            //Set and add Text to spinner
            sexSpinner = new ArrayList<>();
            sexSpinner.add("Männlich");
            sexSpinner.add("Weiblich");
            ArrayAdapter<String> dataAdapter;
            dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexSpinner);
            //Dropdown
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Attaching data adapter to spinner
            sex.setAdapter(dataAdapter);
            //Push editText to array
            elements.add(nachname);
            elements.add(vorname);
            elements.add(address);
            elements.add(krankKasse);
            elements.add(rufNummer);
            elements.add(zimmerNummerEditText);
            //Button
            geburtsTag = findViewById(R.id.datePicker);
            back = findViewById(R.id.imageButton);
        } catch (Exception e) {
            e.printStackTrace();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        date = getTodaysDate();
        geburtsTag.setText(date);
        geburtsTag.setTextColor(getResources().getColor(R.color.black));
        if(ContainerAndGlobal.isDarkmode()){
            geburtsTag.setTextColor(getResources().getColor(R.color.white));
        }

        //Spinner
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sexString = sexSpinner.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        add = findViewById(R.id.submit);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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
                        int phoneNumber =  Integer.parseInt(rufNummer.getText().toString());
                        int zimmerNummer = Integer.parseInt(zimmerNummerEditText.getText().toString());
                        String Sex = sexString;
                        //Assign data to PatientClass
                        PatientClass newPatient;
                        newPatient = new PatientClass(zimmerNummer, nachName, vorName, adresse, Sex, versicherung, birthdate, phoneNumber, "Stabil", 0, "", 0, 0, 0);
                        ContainerAndGlobal.addPatient(newPatient);
                        finish();
                        Toast.makeText(AddPatient.this, " Patient added successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(AddPatient.this, " Invalid Input ", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month =  month+1;
        int day =  cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    month = month+1;
                    String date =  makeDateString(day,month,year);
                    geburtsTag.setText(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day =  cal.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_LIGHT;

            datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year,month,day);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String makeDateString(int day, int month, int year){
        return year+"-"+month+"-"+day;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}