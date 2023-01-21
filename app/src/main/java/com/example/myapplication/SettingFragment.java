package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;


public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Dark Mode Switch
        Switch mode = view.findViewById(R.id.darkMode);


        //Saving state of our app
        SharedPreferences sharedPreferences =  this.requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        //Implementation of the Switch
        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    buttonView.setText("Night Mode");
                    ContainerAndGlobal.setChangedSetting(true);
                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    buttonView.setText("Night Mode");
                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();
                }
            }
        });

        //Set the pre Theme mode when app starts
       ContainerAndGlobal.setDarkmode(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
       mode.setChecked(ContainerAndGlobal.isDarkmode());
        if(ContainerAndGlobal.isDarkmode()){
            mode.setText("Night Mode");
        }else{
            mode.setText("Night Mode");
        }

    }

}
