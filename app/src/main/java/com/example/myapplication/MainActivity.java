package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button A;
    TextView B;
    Button A2;
    Switch darkmode;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B=findViewById(R.id.Username);
        A=findViewById(R.id.Login);
        darkmode=findViewById(R.id.Darkmode);
        Intent intent = new Intent(this, MainActivity2.class);
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        darkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    darkmode.setText("Dark mode");
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    darkmode.setText("Light mode");
                }
            }
        });
        //set night mode when app starts?
        boolean isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
            darkmode.setChecked(isNightModeOn);
            if(isNightModeOn){
                darkmode.setText("Night Mode");
            }else{
                darkmode.setText("Light Mode");
            }
    }
}