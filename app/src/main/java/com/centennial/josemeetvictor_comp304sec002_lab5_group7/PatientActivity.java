package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class PatientActivity extends AppCompatActivity {

    public static final int ADD_BOOK_REQUEST = 1;
    public static final int EDIT_BOOK_REQUEST = 2;
    PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

    }
}