package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AddPatient extends AppCompatActivity {

    private EditText id_p;
    private EditText name_p;
    private EditText age_p;
    private EditText bill_p;
    private EditText disease_p;

    PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        setTitle("Add a new Patient");

        id_p = findViewById(R.id.edit_text_id);
        name_p = findViewById(R.id.edit_text_name);
        age_p = findViewById(R.id.edit_text_age);
        bill_p = findViewById(R.id.edit_text_bill);
        disease_p = findViewById(R.id.edit_text_disease);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

    }

    private void savePatient() {
        String id = id_p.getText().toString();
        String name = name_p.getText().toString();
        String age = age_p.getText().toString();
        String bill = bill_p.getText().toString();
        String disease = disease_p.getText().toString();

        if (name.trim().isEmpty() || age.trim().isEmpty() || bill.trim().isEmpty() || disease.trim().isEmpty()) {
            Toast.makeText(this, "Please insert complete information.", Toast.LENGTH_SHORT).show();
        } else {
            int i = Integer.parseInt(id);
            int a = Integer.parseInt(age);
            int b = Integer.parseInt(bill);

            Patient patient = new Patient(i, name, a, disease, b);
            patientViewModel.insert(patient);

            Intent intent = new Intent(AddPatient.this, PatientActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_patient:
                savePatient();
                Toast.makeText(this, "Patient Added!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}