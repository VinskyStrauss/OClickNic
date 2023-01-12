package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContainerAndGlobal.isFirstTime()) {
            ContainerAndGlobal.setFirstTime(false);
            //Read JSON
            try {
                String jsonString = ContainerAndGlobal.getJSONData(this, "PatientList.json");
                JSONArray jsonarray = new JSONArray(jsonString);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject json_inside = jsonarray.getJSONObject(i);
                    ContainerAndGlobal.parseLadesaeuleObject(json_inside);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Create Fragment
        final HomeFragment homeFragment = new HomeFragment();
        final SettingFragment settingFragment = new SettingFragment();
        final InfoFragment infoFragment = new InfoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
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
    }

    public void replaceFragment(Fragment x){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, x).commit();
    }


}