package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {

    private final PatientDao patientDao;

    public PatientRepository(Context context) { patientDao = PatientDao.getInstance();}

    public void insert(Patient patient) { patientDao.insert(patient);}

    public void delete(int id) { patientDao.delete(id);}

    public void update(int iD, String name, int ageNew, String disease, int billNew) {
        patientDao.update(iD, name, ageNew, disease, billNew);
    }
}
