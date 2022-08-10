package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository patientRepository;

    public PatientViewModel(@NonNull Application application)
    {
        super(application);
        patientRepository = new PatientRepository(application);
    }

    public void insert(Patient patient) { patientRepository.insert(patient);}

    public void delete(int id) { patientRepository.delete(id);}

    public void update(int iD, String name, int ageNew, String disease, int billNew) {
        patientRepository.update(iD, name, ageNew, disease, billNew);
    }
}
