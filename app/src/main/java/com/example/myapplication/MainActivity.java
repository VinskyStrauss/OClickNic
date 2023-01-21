package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContainerAndGlobal.isFirstTime()) {
            ContainerAndGlobal.setFirstTime(false);
            getNewPatientList();
            if(ContainerAndGlobal.getPatientListsVerwalter().size() == 0 && ContainerAndGlobal.getPatientLists().size() == 0){
                //Read JSON
                try {
                    String jsonString = ContainerAndGlobal.getJSONData(this, "PatientList.json");
                    JSONArray jsonarray = new JSONArray(jsonString);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject json_inside = jsonarray.getJSONObject(i);
                        ContainerAndGlobal.parsePatientObject(json_inside);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                ArrayList<String> bloodTest = ContainerAndGlobal.readCSV(this, "BloodValue.csv");
                ContainerAndGlobal.addCSVtoBloodValue(bloodTest);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //Create Fragment
        final HomeFragment homeFragment = new HomeFragment();
        final SettingFragment settingFragment = new SettingFragment();
        final InfoFragment infoFragment = new InfoFragment();
        //BottomNavigationBar
        bottomNavigationView = findViewById(R.id.bottomnavigationbar);
        bottomNavigationView.setSelectedItemId(R.id.home);
        //implementation of BottomNavigationBar
        bottomNavigationView.setOnItemSelectedListener(item ->  {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
                        return true;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, settingFragment).commit();
                        return true;
                    case R.id.info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, infoFragment).commit();
                        return true;
                }
                return false;
        });
        if(!ContainerAndGlobal.isChangedSetting()) {
            bottomNavigationView.setSelectedItemId(R.id.home);
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, settingFragment).commit();
            ContainerAndGlobal.setChangedSetting(false);
        }
        // Saving state of our app
        // using SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        ContainerAndGlobal.setDarkmode(sharedPreferences.getBoolean("isDarkModeOn", false));
        // When user reopens the app
        // after applying dark/light mode
        if (ContainerAndGlobal.isDarkmode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    /**
     * function to change Fragment
     * @param x
     */
    public void replaceFragment(Fragment x){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, x).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            ContainerAndGlobal.saveData(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            ContainerAndGlobal.saveData(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            ContainerAndGlobal.saveData(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * function to get the actual patient list
     */
    private void getNewPatientList(){
        try{
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Gson gson = new Gson();
            String json = sharedPrefs.getString("PatientList","");
            Type type = new TypeToken<List<PatientClass>>() {}.getType();
            List<PatientClass> newList = gson.fromJson(json,type);
            if(newList != null){
                for(int i=0; i<newList.size(); i++){
                    ContainerAndGlobal.addPatient(newList.get(i));
                    if(newList.get(i).getMrt() == 1){
                        ContainerAndGlobal.addPatientMRT(newList.get(i));
                    }
                    if(newList.get(i).getBlood() == 1){
                        ContainerAndGlobal.addPatientBloodTest(newList.get(i));
                    }
                }
            }
        }catch(JsonSyntaxException e){
            e.printStackTrace();
        }
    }

}