package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class DarkModeActivity extends AppCompatActivity {
    Button darkmode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_mode);
        darkmode = findViewById(R.id.DarkButton);

        // Saving state of our app
        // using SharedPreferences
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        // When user reopens the app
        // after applying dark/light mode
        if (ContainerAndGlobal.isDarkmode()) {
            darkmode.setText("disable_darkmode");
        }
        else {
            darkmode.setText("enable_darkmode");
        }

        // Implementation of dark mode button
        darkmode.setOnClickListener(v -> {
            if (ContainerAndGlobal.isDarkmode()) {
                // if dark mode is on it
                // will turn it off
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                // it will set isDarkModeOn
                // boolean to false
                editor.putBoolean("isDarkModeOn", false);
                editor.apply();
                // change text of Button
                darkmode.setText("enable_darkmode");
            }
            else {
                // if dark mode is off
                // it will turn it on
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                // it will set isDarkModeOn
                // boolean to true
                editor.putBoolean("isDarkModeOn", true);
                editor.apply();
                // change text of Button
                darkmode.setText("disable_darkmode");
            }
            ContainerAndGlobal.setChangedSetting(true);
        });
    }
}