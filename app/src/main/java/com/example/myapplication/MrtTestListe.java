package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class MrtTestListe extends AppCompatActivity implements RecyclerViewInterface{
    RecyclerView list;
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
        list =  findViewById(R.id.list_item_mrt);
        TestListAdapter listAdapter = new TestListAdapter(this, ContainerAndGlobal.getMrtPatient(), this);
        list.setAdapter(listAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        }

    @Override
    protected void onResume() {
        super.onResume();
        TestListAdapter listAdapter = new TestListAdapter(this, ContainerAndGlobal.getMrtPatient(),this);
        list.setAdapter(listAdapter);
    }


    @Override
    public void onItemClick(int position) {
        ContainerAndGlobal.savePosition(position);
        startActivity(new Intent(MrtTestListe.this,MrtResultActivity.class));
    }
}