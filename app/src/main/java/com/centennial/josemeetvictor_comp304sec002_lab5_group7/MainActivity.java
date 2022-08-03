package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        Patient p = new Patient("Jose Efren Hernandez", 23, "Flu", 17);
        patientViewModel.insert(p);


    }
}