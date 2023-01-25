package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class BlutTestResultActivity extends AppCompatActivity {
    int pos =  ContainerAndGlobal.getPosition();
    PatientClass patientClass = ContainerAndGlobal.getBloodPatient().get(pos);
    BloodValueClass bloodValueClass;
    TextView date;
    TextView leukozyten;
    TextView lymphozytenPer;
    TextView lymphozytenAbs;
    //Assign Imageview and button
    ImageView back;
    Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blut_test_result);
        try {
            //Assign Textview ID
            date = findViewById(R.id.dateText);
            leukozyten = findViewById(R.id.leukozytenText);
            lymphozytenPer = findViewById(R.id.lymphozytenPercentText);
            lymphozytenAbs = findViewById(R.id.lymphozytenAbsolutText);
            //Assign Imageview and button
            back = findViewById(R.id.back);
            upload = findViewById(R.id.upload);
            //Random Generator
            Random random = new Random();
            int upperbound=ContainerAndGlobal.getBloodValue().size();
            int random_pos = random.nextInt(upperbound);
            //Blood Value
            bloodValueClass = ContainerAndGlobal.getBloodValue().get(random_pos);
            //Set Textview text
            date.setText(bloodValueClass.getDatum());
            leukozyten.setText(String.valueOf(bloodValueClass.getLeukozyten()));
            lymphozytenPer.setText(String.valueOf(bloodValueClass.getLymphozytenPercent()));
            lymphozytenAbs.setText(String.valueOf(bloodValueClass.getLymphozytenAbsolut()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Upload button
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    patientClass.setSeeBlood(1);
                    patientClass.setBlood(0);
                    patientClass.setBloodValueClass(bloodValueClass);
                    ContainerAndGlobal.deleteFromBlood(patientClass);
                    finish();
                    Toast.makeText(BlutTestResultActivity.this ,"Result uploaded to the Doctor ", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}