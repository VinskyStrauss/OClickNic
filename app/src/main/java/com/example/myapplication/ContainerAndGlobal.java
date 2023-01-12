package com.example.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContainerAndGlobal {
    private static ArrayList<PatientClass> patientLists = new ArrayList<>();
    private static ArrayList<PatientClass> patientListsVerwalter = new ArrayList<>();
    private static ArrayList<PatientClass> mrtPatient = new ArrayList<>();

    public static ArrayList<PatientClass> getPatientLists() {
        return patientLists;
    }

    public static ArrayList<PatientClass> getPatientListsVerwalter() {
        return patientListsVerwalter;
    }

    public static ArrayList<PatientClass> getMrtPatient(){
        return mrtPatient;
    }

    private static int stelle;
    private static boolean firstTime = true;

    //Function to read JSON file
    /**
     * Get JSON data in string format
     *
     * @param context      is the activity that runs it
     * @param textFileName is the name of the JSON file
     * @return the JSON file as a string file
     */
    @NonNull
    public static String getJSONData(Context context, String textFileName) {
        String strJSON;
        StringBuilder buf = new StringBuilder();
        InputStream json;
        try {
            json = context.getAssets().open(textFileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(json, StandardCharsets.UTF_8));

            while ((strJSON = in.readLine()) != null) {
                buf.append(strJSON);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }


    public static void addPatient(PatientClass patient) {
        if (patient.getStatus().equals("Krank")) {
            patientLists.add(patient);
        }
        patientListsVerwalter.add(patient);
    }

    public static void addPatientMRT(PatientClass patientClass){
        mrtPatient.add(patientClass);
    }

    /**
     * Convert from JSON file into a java class
     *
     * @param patientclass is the JSON object of the charging station
     * @throws JSONException if it is error
     */
    public static void parseLadesaeuleObject(JSONObject patientclass) throws JSONException {
        PatientClass tmpPatient = new PatientClass(
                Integer.parseInt(patientclass.get("Patient ID").toString()),
                (String) patientclass.get("Nachname"),
                (String) patientclass.get("Vorname"),
                (String) patientclass.get("Adresse"),
                (String) patientclass.get("Geschlecht"),
                Integer.parseInt(patientclass.get("Versicherungsnummer").toString()),
                (String) patientclass.get("Geburtstag"),
                (String) patientclass.get("Rufnummer"),
                (String) patientclass.get("Status"),
                Integer.parseInt(patientclass.get("MRT").toString()),
                (String) patientclass.get("Description"),
                Integer.parseInt(patientclass.get("SeeMRT").toString())

        );
        addPatient(tmpPatient);
    }

    public static List<String> patientListeToStringList(ArrayList<PatientClass> patientList) {
        List<String> patientListe = new ArrayList<>();
        patientList.forEach((patient) -> patientListe.add(patient.getNachname() + ", " + patient.getVorname()));
        return patientListe;
    }

    public static void savePosition(int Pos) {
        stelle = Pos;
    }

    public static int getPosition() {
        return stelle;
    }

    public static boolean isFirstTime() {
        return firstTime;
    }

    public static void setFirstTime(boolean firstTime) {
        ContainerAndGlobal.firstTime = firstTime;
    }

    /**
     * function to read csv data
     *
     * @param dataPath
     */
    public static void readCSV(String dataPath) {
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(dataPath))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] data = line.split(csvSplitBy);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Check if Patient already in list
    public static boolean checkPatient(PatientClass patientClass,ArrayList<PatientClass> arrayList){
        for(int i=0; i<arrayList.size(); i++){
            if(patientClass.getVorname().equals(arrayList.get(i).getVorname()) && patientClass.getNachname().equals(arrayList.get(i).getNachname())){
                return false;
            }
        }
        return true;
    }

}

