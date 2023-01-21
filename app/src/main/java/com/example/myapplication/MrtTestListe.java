package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;



public class MrtTestListe extends AppCompatActivity {
    List<String> patientListe;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrt_test_liste);

        //Back
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Check the result from MRT test
        listView = findViewById(R.id.testList);
        patientListe = ContainerAndGlobal.patientListeToStringList(ContainerAndGlobal.getMrtPatient());
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,patientListe);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ContainerAndGlobal.savePosition(position);
                newActivity(position);
            };
        });

        }

    @Override
    protected void onResume() {
        super.onResume();
        patientListe = ContainerAndGlobal.patientListeToStringList(ContainerAndGlobal.getMrtPatient());
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,patientListe);
        listView.setAdapter(arrayAdapter);
    }

    /**
     * function to start a new Activity
     * @param patientPos
     * @return
     */
    private View.OnClickListener newActivity(int patientPos){
        startActivity(new Intent(MrtTestListe.this,MrtResultActivity.class));
        return null;
    }
}