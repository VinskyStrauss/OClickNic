package com.example.myapplication;

public class PatientClass {


    public PatientClass(int zimmerNummer, String nachname, String vorname, String adresse, String sex, int versicherungsnummer, String birthday, String rufnummer, String status, int mrt, String bemerkung, int seemrt) {
        this.zimmerNum = zimmerNummer;
        this.nachname = nachname;
        this.vorname = vorname;
        this.adresse = adresse;
        this.sex = sex;
        this.versicherungsnummer = versicherungsnummer;
        this.birthday = birthday;
        this.rufnummer = rufnummer;
        this.status = status;
        this.mrt = mrt;
        this.bemerkung = bemerkung;
        this.seemrt = seemrt;
    }
    private final int zimmerNum;
    private final String nachname;
    private final String vorname;
    private final String adresse;
    private final String sex;
    private final int versicherungsnummer;
    private final String birthday;
    private final String rufnummer;
    private String status;
    private String bemerkung;
    private int mrt;
    private int seemrt;


    public int getZimmerNum() {
        return zimmerNum;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSex() {
        return sex;
    }

    public int getVersicherungsnummer() {
        return versicherungsnummer;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getRufnummer() {
        return rufnummer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMrt() {
        return mrt;
    }

    public void setMrt(int mrt) {
        this.mrt = mrt;
    }

    public void setBemerkung(String bemerkung){
        this.bemerkung = bemerkung;
    }
    public String getBemerkung(){
        return bemerkung;
    }

    public void setSeeMrt(int Set){
        this.seemrt = Set;
    }
    public int getSeemrt(){
        return seemrt;
    }
}
