package com.example.myapplication;

public class BloodValueClass {
    //Attribut
    private final String datum;
    private final double leukozyten;
    private final double lymphozytenPercent;
    private final double lymphozytenAbsolut;

    public String getDatum() {
        return datum;
    }

    public double getLeukozyten() {
        return leukozyten;
    }

    public double getLymphozytenPercent() {
        return lymphozytenPercent;
    }

    public double getLymphozytenAbsolut() {
        return lymphozytenAbsolut;
    }
    public BloodValueClass(String datum, double leukozyten, double lymphozytenPercent, double lymphozytenAbsolut) {
        this.datum = datum;
        this.leukozyten = leukozyten;
        this.lymphozytenPercent = lymphozytenPercent;
        this.lymphozytenAbsolut = lymphozytenAbsolut;
    }


}
