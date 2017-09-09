package com.example.vobis.myapplication.BMI;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Vobis on 2015-08-05.
 */
public class Patients implements Serializable {
    int index;
    ArrayList<String> patientsName = new ArrayList<String>();
    ArrayList<String> patientsSurname = new ArrayList<String>();
    ArrayList<String> patientsResult = new ArrayList<String>();
}
