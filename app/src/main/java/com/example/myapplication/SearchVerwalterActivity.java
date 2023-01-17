package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

public class SearchVerwalterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_verwalter);
        //Declare Variable
        SearchView search = findViewById(R.id.search_bar);
        ImageView back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //List of Patient
        ListView list =  findViewById(R.id.list_item);
        List<String> patientListe = ContainerAndGlobal.patientListeToStringList(ContainerAndGlobal.getPatientListsVerwalter());
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,patientListe);
        list.setSelector(R.color.blue);
        list.setAdapter(arrayAdapter);
        //Search bar
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        //Set tap on List
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ContainerAndGlobal.savePosition(position);
                newActivity(position);
            };
        });


    }
    private View.OnClickListener newActivity(int patientPos){
        startActivity(new Intent(SearchVerwalterActivity.this,PatientDataVerwalter.class));
        return null;
    }
}