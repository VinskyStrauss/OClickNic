package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ContainerAndGlobal {
    private static ArrayList<PatientClass> patientLists = new ArrayList<>();
    private static ArrayList<PatientClass> patientListsVerwalter = new ArrayList<>();
    private static ArrayList<PatientClass> mrtPatient = new ArrayList<>();

    public static boolean isChangedSetting() {
        return changedSetting;
    }

    public static void setChangedSetting(boolean changedSetting) {
        ContainerAndGlobal.changedSetting = changedSetting;
    }

    private static boolean changedSetting = false;
    
    
    //Max bed size
    private static int maxBed = 100;
    
    public static boolean isFormContainsEmptyElement(ArrayList<EditText> elements){
        boolean empty = false;
        for(EditText e:elements){
            if(e.getText().toString().isEmpty()){
                e.setError("Cannot be blank");
                empty = true;
            }
        }
        return empty;
    }

    public static boolean isDataDuplicate(ArrayList<EditText> elements){
        boolean duplicate = false;
        int insurance = Integer.parseInt(elements.get(3).getText().toString());
        String phone = elements.get(5).getText().toString();
        int zimmerNummer = Integer.parseInt(elements.get(6).getText().toString());
        for(PatientClass patient:patientListsVerwalter){
            if(patient.getVersicherungsnummer() == insurance){
                elements.get(3).setError("Insurance number already existed");
                duplicate = true;
            }
            if(patient.getRufnummer().equals(phone)){
                elements.get(5).setError("Phone number already existed");
                duplicate = true;
            }
            if(patient.getZimmerNum() == zimmerNummer){
                elements.get(6).setError("Bed is already occupied");
                duplicate = true;
            }
        }
        return duplicate;
    }

    
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
        patientList.forEach((patient) -> patientListe.add(patient.getVorname() + " " + patient.getNachname() + ", Zimmer:" + patient.getZimmerNum()));
        return patientListe;
    }

    public static void savePosition(int Pos) {
        stelle = Pos;
    }

    public static void saveName(String name){

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

    //Delete if patient is healed
    public static void deletePatientDoctor(PatientClass patient){
        for(int i=0; i<patientLists.size(); i++){
            if(patientLists.get(i).getStatus().equals("Geheilt")){
                patientLists.remove(i);
            }
        }
    }

    //Delete after mrt test
    public static void deleteFromMrt(PatientClass patient){
        for(int i=0; i<mrtPatient.size(); i++){
            if(mrtPatient.get(i).getNachname().equals(patient.getNachname()) && mrtPatient.get(i).getVorname().equals(patient.getVorname())){
                mrtPatient.remove(i);
            }
        }
    }

    //Check Discharged Patient
    public static boolean checkDischarged(PatientClass patient){
        if(patient.getStatus().equals("Geheilt")){
            return true;
        }
        return false;
    }
    //Discharged Patient
    public static void deletePatient(PatientClass patientClass){
        for(int i=0; i<patientListsVerwalter.size(); i++){
            if(patientListsVerwalter.get(i).getNachname().equals(patientClass.getNachname()) && patientListsVerwalter.get(i).getVorname().equals(patientClass.getVorname())){
                patientListsVerwalter.remove(i);
            }
        }
    }
    //Search Function
    public static int searchPatient(String name){
        for(int i=0; i<patientLists.size(); i++){
            if((patientLists.get(i).getVorname().concat(" " +patientLists.get(i).getNachname())).contains(name)){
                return i;
            }
        }
        return -1;
    }
    public static int searchPatientVerwalter(String name){
        for(int i=0; i<patientListsVerwalter.size(); i++){
            if((patientListsVerwalter.get(i).getVorname().concat(" " +patientListsVerwalter.get(i).getNachname())).contains(name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * A fucntion to save the new data
     * @param context
     */
    public static void saveData(Context context){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor =sharedPrefs.edit();
        Gson gson =  new Gson();
        String json;
        json = gson.toJson(ContainerAndGlobal.getPatientListsVerwalter());
        editor.apply();


    }
}

