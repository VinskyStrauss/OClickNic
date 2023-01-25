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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContainerAndGlobal {
    private static ArrayList<PatientClass> patientLists = new ArrayList<>();
    private static ArrayList<PatientClass> patientListsVerwalter = new ArrayList<>();
    private static ArrayList<PatientClass> mrtPatient = new ArrayList<>();
    private static ArrayList<PatientClass> bloodPatient = new ArrayList<>();
    private static ArrayList<BloodValueClass> bloodValue = new ArrayList<>();
    private static final ArrayList<PatientClass> filteredList = new ArrayList<>();
    private static boolean changedSetting = false;
    private static PatientClass tmpPatient;
    private static boolean darkmode = false;
    private static PatientClass patientSearch;
    public static boolean isChangedSetting() {
        return changedSetting;
    }

    public static void setChangedSetting(boolean changedSetting) {
        ContainerAndGlobal.changedSetting = changedSetting;
    }

    public static PatientClass getTmpPatient() {
        return tmpPatient;
    }

    public static void setTmpPatient(PatientClass tmpPatient) {
        ContainerAndGlobal.tmpPatient = tmpPatient;
    }


    /**
     * function to check is the form is filled correctly or not
     * @param elements
     * @return
     */
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

    /**
     * function to check new patient data
     * @param elements
     * @return
     */
    public static boolean isDataDuplicate(ArrayList<EditText> elements){
        boolean duplicate = false;
        int insurance = Integer.parseInt(elements.get(3).getText().toString());
        int phone = Integer.parseInt(elements.get(4).getText().toString());
        int zimmerNummer = Integer.parseInt(elements.get(5).getText().toString());
        for(PatientClass patient:patientListsVerwalter){
            if(patient.getVersicherungsnummer() == insurance){
                elements.get(3).setError("Insurance number already existed");
                duplicate = true;
            }
            if(patient.getRufnummer() == phone){
                elements.get(4).setError("Phone number already existed");
                duplicate = true;
            }
            if(patient.getZimmerNum() == zimmerNummer){
                elements.get(5).setError("Bed is already occupied");
                duplicate = true;
            }
        }
        return duplicate;
    }

    /**
     * getter Function for doctor list
     * @return
     */
    public static ArrayList<PatientClass> getPatientLists() {
        return patientLists;
    }

    /**
     * getter function for admin list
     * @return
     */
    public static ArrayList<PatientClass> getPatientListsVerwalter() {
        return patientListsVerwalter;
    }

    /**
     * getter function for mrt list
     * @return
     */
    public static ArrayList<PatientClass> getMrtPatient(){
        return mrtPatient;
    }

    /**
     * getter function for blood test
     * @return
     */
    public static ArrayList<PatientClass> getBloodPatient() {
        return bloodPatient;
    }

    /**
     * getter function for blood list
     * @return
     */
    public static ArrayList<BloodValueClass> getBloodValue() {
        return bloodValue;
    }


    /**
     * getter function for filtered List
     * @return
     */
    public static ArrayList<PatientClass> getFilteredList() {
        return filteredList;
    }

    /**
     * position for patient
     */
    private static int stelle;
    /**
     * is it the first time the application start?
     */
    private static boolean firstTime = true;

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

    /**
     * function to add patient to doctor and admin list
     * @param patient
     */
    public static void addPatient(PatientClass patient) {
        if (!patient.getStatus().toLowerCase(Locale.ROOT).equals("healed") ) {
            patientLists.add(patient);
        }
        patientListsVerwalter.add(patient);
    }

    /**
     * function to add patient to mrt list
     * @param patientClass
     */
    public static void addPatientMRT(PatientClass patientClass){
        mrtPatient.add(patientClass);
    }

    /**
     * function to add patient to blood list
     * @param patientClass
     */
    public static void addPatientBloodTest(PatientClass patientClass){
        bloodPatient.add(patientClass);
    }

    /**
     * Convert from JSON file into a java class
     *
     * @param patientclass is the JSON object of the charging station
     * @throws JSONException if it is error
     */
    public static void parsePatientObject(JSONObject patientclass) throws JSONException {
        PatientClass tmpPatient = new PatientClass(
                Integer.parseInt(patientclass.get("Patient ID").toString()),
                (String) patientclass.get("Nachname"),
                (String) patientclass.get("Vorname"),
                (String) patientclass.get("Adresse"),
                (String) patientclass.get("Geschlecht"),
                Integer.parseInt(patientclass.get("Versicherungsnummer").toString()),
                (String) patientclass.get("Geburtstag"),
                Integer.parseInt(patientclass.get("Rufnummer").toString()),
                (String) patientclass.get("Status"),
                Integer.parseInt(patientclass.get("MRT").toString()),
                (String) patientclass.get("Description"),
                Integer.parseInt(patientclass.get("SeeMRT").toString()),
                Integer.parseInt(patientclass.get("Blood").toString()),
                Integer.parseInt(patientclass.get("seeBlood").toString())

        );
        addPatient(tmpPatient);
    }

    /**
     * function to change patient list to string list
     * @param patientList
     * @return patientListe
     */
    public static List<String> patientListeToStringList(ArrayList<PatientClass> patientList) {
        List<String> patientListe = new ArrayList<>();
        patientList.forEach((patient) -> patientListe.add(patient.getVorname() + " " + patient.getNachname() + ", Zimmer:" + patient.getZimmerNum()));
        return patientListe;
    }

    /**
     * function to save the patient position
     * @param Pos
     */
    public static void savePosition(int Pos) {
        stelle = Pos;
    }

    /**
     * function to get patient position in the list
     * @return stelle
     */
    public static int getPosition() {
        return stelle;
    }

    /**
     * function to get firstTime
     * @return firstTime
     */
    public static boolean isFirstTime() {
        return firstTime;
    }

    /**
     * function to set firstTime
     * @param firstTime
     */
    public static void setFirstTime(boolean firstTime) {
        ContainerAndGlobal.firstTime = firstTime;
    }

    public static boolean isDarkmode() {
        return darkmode;
    }

    public static void setDarkmode(boolean darkmode) {
        ContainerAndGlobal.darkmode = darkmode;
    }


    public static void setPatientSearch(PatientClass patientSearch){
        ContainerAndGlobal.patientSearch  = patientSearch;
    }

    public static PatientClass getPatientSearch(){
        return patientSearch;
    }

    /**
     * function to read CSV file
     * @param context
     * @param textFileName
     * @return ArrayList
     */
    public static ArrayList<String> readCSV(Context context, String textFileName) {
        String string;
        ArrayList<String> row = new ArrayList<>();
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(textFileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((string = in.readLine()) != null) {
                row.add(string);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * function to set CSV value to bloodValueClass and add to list
     * @param bloodTest
     */
    public static void addCSVtoBloodValue(ArrayList<String> bloodTest){
        String splitBy = ";";
        double leuko;
        double lympPer;
        double lympAbs;
        for(int i=1; i<bloodTest.size(); i++){
            String[] data = bloodTest.get(i).split(splitBy);
            String date = data[0];
            leuko = Double.parseDouble(data[1]);
            lympPer = Double.parseDouble(data[2]);
            lympAbs = Double.parseDouble(data[3]);
            BloodValueClass bloodValueClass = new BloodValueClass(date,leuko,lympPer,lympAbs);
            bloodValue.add(bloodValueClass);
        }
    }


    /**
     * function to check if patient already in the list
     * @param patientClass
     * @param arrayList
     * @return boolean
     */
    public static boolean checkPatient(PatientClass patientClass,ArrayList<PatientClass> arrayList){
        for(int i=0; i<arrayList.size(); i++){
            if(patientClass.getVorname().equals(arrayList.get(i).getVorname()) && patientClass.getNachname().equals(arrayList.get(i).getNachname())){
                return false;
            }
        }
        return true;
    }

    /**
     * function to delete patient if patient is already healed
     * @param patient
     */
    public static void deletePatientDoctor(PatientClass patient){
        for(int i=0; i<patientLists.size(); i++){
            if(patientLists.get(i).getStatus().equals("Healed")){
                patientLists.remove(i);
            }
        }
    }

    /**
     * function to delete patient after mrt test
     * @param patient
     */
    public static void deleteFromMrt(PatientClass patient){
        for(int i=0; i<mrtPatient.size(); i++){
            if(mrtPatient.get(i).getNachname().equals(patient.getNachname()) && mrtPatient.get(i).getVorname().equals(patient.getVorname())){
                mrtPatient.remove(i);
            }
        }
    }

    /**
     * function to delete patient after blood test
     * @param patient
     */
    public static void deleteFromBlood(PatientClass patient){
        for(int i=0; i<bloodPatient.size(); i++){
            if(bloodPatient.get(i).getNachname().equals(patient.getNachname()) && bloodPatient.get(i).getVorname().equals(patient.getVorname())){
                bloodPatient.remove(i);
            }
        }
    }

    /**
     * function to check if patient can be discharge
     * @param patient
     * @return boolean
     */
    public static boolean checkDischarged(PatientClass patient){
        if(patient.getStatus().equals("Healed")){
            return true;
        }
        return false;
    }

    /**
     * function to discharge patient
     * @param patientClass
     */
    public static void deletePatient(PatientClass patientClass){
        for(int i=0; i<patientListsVerwalter.size(); i++){
            if(patientListsVerwalter.get(i).getNachname().equals(patientClass.getNachname()) && patientListsVerwalter.get(i).getVorname().equals(patientClass.getVorname())){
                patientListsVerwalter.remove(i);
            }
        }
    }

    /**
     * function to search specific patient in doctor list
     * @param name
     * @return int
     */
    public static int searchPatient(String name){
        for(int i=0; i<patientLists.size(); i++){
            if((patientLists.get(i).getVorname().concat(" " +patientLists.get(i).getNachname())).contains(name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * function to search specific patient in admin list
     * @param name
     * @return int
     */
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
        editor.putString("PatientList",json);
        editor.apply();
    }
}

