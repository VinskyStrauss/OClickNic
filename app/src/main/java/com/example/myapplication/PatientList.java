package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


@SuppressLint("SimpleDateFormat")
public class PatientList extends AppCompatActivity {
    ListView list;
    List<String> patientListe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        show();
    }


    public void show()
    {
        setContentView(R.layout.activity_patient_list);
        //Back
        ImageView back = findViewById(R.id.imageView3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list =  findViewById(R.id.list_item);
        patientListe = ContainerAndGlobal.patientListeToStringList(ContainerAndGlobal.getPatientLists());
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,patientListe);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              ContainerAndGlobal.savePosition(position);
              newActivity(position);
            };
        });

        if(patientListe.size() == 0){
            Toast.makeText(PatientList.this, "No Patient Available", Toast.LENGTH_LONG).show();
        }

    }

    private View.OnClickListener newActivity(int patientPos){
                startActivity(new Intent(PatientList.this,PatientData.class));
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        patientListe = ContainerAndGlobal.patientListeToStringList(ContainerAndGlobal.getPatientLists());
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,patientListe);
        list.setAdapter(arrayAdapter);
    }


}
