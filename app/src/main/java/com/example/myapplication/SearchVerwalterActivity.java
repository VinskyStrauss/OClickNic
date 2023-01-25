package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchVerwalterActivity extends AppCompatActivity implements RecyclerViewInterface{

    private ListAdapter listAdapter;

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
        RecyclerView list =  findViewById(R.id.list_item);
        listAdapter = new ListAdapter(this, ContainerAndGlobal.getPatientListsVerwalter(), this);
        list.setAdapter(listAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        ContainerAndGlobal.getFilteredList().clear();
        ContainerAndGlobal.getFilteredList().addAll(ContainerAndGlobal.getPatientListsVerwalter());
        //Search bar
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


    }
    /**
     * Filtering the list that contains the word from the searched words
     * @param text is the current searched address
     */
    private void filter(String text) {
        try {
            // creating a new array list to filter our data.
            ArrayList<PatientClass> filteredList = new ArrayList<>();

            // running a for loop to compare elements.
            for (PatientClass item : ContainerAndGlobal.getFilteredList()) {
                // checking if the entered string matched with any item of our recycler view.
                String name = item.getNachname() + " , " + item.getVorname();
                String zimmer = " Zimmer Nummer: " + Integer.toString(item.getZimmerNum());
                if (name.toLowerCase().contains(text.toLowerCase()) || zimmer.toLowerCase().contains(text.toLowerCase())) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredList.add(item);
                }
            }
            listAdapter.filterList(filteredList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(SearchVerwalterActivity.this,PatientDataVerwalter.class));
    }
}