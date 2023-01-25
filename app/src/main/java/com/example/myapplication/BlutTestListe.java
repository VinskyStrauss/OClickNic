package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BlutTestListe extends AppCompatActivity implements RecyclerViewInterface{
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blut_test_liste);
        //Back
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list = findViewById(R.id.list_item_blut);
        TestListAdapter listAdapter = new TestListAdapter(this, ContainerAndGlobal.getBloodPatient(), this);
        list.setAdapter(listAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onResume() {
        super.onResume();
        TestListAdapter listAdapter = new TestListAdapter(this, ContainerAndGlobal.getBloodPatient(),this);
        list.setAdapter(listAdapter);
    }


    @Override
    public void onItemClick(int position) {
        ContainerAndGlobal.savePosition(position);
        startActivity(new Intent(BlutTestListe.this,BlutTestResultActivity.class));
    }
}