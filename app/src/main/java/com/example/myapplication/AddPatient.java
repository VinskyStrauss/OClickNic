package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class AddPatient extends AppCompatActivity {
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        add = findViewById(R.id.submit);
        Intent intent = new Intent(this , VerwalterActivity.class);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}