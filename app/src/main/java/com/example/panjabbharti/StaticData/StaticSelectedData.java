package com.example.panjabbharti.StaticData;

import java.util.ArrayList;

public class StaticSelectedData {
    public static ArrayList<String> selectedQualification;
    public static ArrayList<String> selectedAge;
    public static ArrayList<String> selectedPanjabi;

    public static void resetData(){
        selectedQualification=new ArrayList<>();
        selectedAge=new ArrayList<>();
        selectedPanjabi=new ArrayList<>();
    }
}
