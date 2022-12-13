package com.example.myapplication;

public class ContainerAndGlobal {
    private static boolean darkmode = false;
    private static boolean changedSetting = false;

    public static boolean isDarkmode() {
        return darkmode;
    }
    public static void setChangedSetting(boolean changedSetting) {
        ContainerAndGlobal.changedSetting = changedSetting;
    }
    public static void setDarkmode(boolean darkmode) {
        ContainerAndGlobal.darkmode = darkmode;
    }
}
