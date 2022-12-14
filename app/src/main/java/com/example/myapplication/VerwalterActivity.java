package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
    Button plus;
    Button viewList;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        plus=findViewById(R.id.button2);
        viewList=findViewById(R.id.button);
        Intent addpatient = new Intent(this, AddPatient.class);
        Intent intent =  new Intent(this, ArztPatientActivity.class);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addpatient);
            }
        });
        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        //BottomNavigationBar
        bottomNavigationView = findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        //implementation of BottomNavigationBar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        return true;
                    case R.id.darkmode:
                        startActivity(new Intent(getApplicationContext()
                                ,DarkModeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext()
                                ,Info.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}