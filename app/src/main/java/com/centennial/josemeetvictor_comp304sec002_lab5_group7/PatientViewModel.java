package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository patientRepository;

    public PatientViewModel(@NonNull Application application)
    {
        super(application);
        patientRepository = new PatientRepository(application);
    }

    public void insert(Patient patient) { patientRepository.insert(patient);}
}
