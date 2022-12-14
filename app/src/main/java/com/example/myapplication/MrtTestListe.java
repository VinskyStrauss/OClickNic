package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MrtTestListe extends AppCompatActivity {
    Button result;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrt_test_liste);
        result = findViewById(R.id.viewResult);




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