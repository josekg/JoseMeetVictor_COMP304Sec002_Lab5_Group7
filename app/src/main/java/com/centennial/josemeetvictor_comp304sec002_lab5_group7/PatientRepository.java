package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.content.Context;

public class PatientRepository {

    private final PatientDao patientDao;

    public PatientRepository(Context context) { patientDao = PatientDao.getInstance();}

    public void insert(Patient patient) { patientDao.insert(patient);}
}
