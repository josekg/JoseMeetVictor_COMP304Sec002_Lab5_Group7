package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class EditPatient extends AppCompatActivity {

    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_AGE = "EXTRA_AGE";
    public static final String EXTRA_BILL = "EXTRA_BILL";
    public static final String EXTRA_DISEASE = "EXTRA_DISEASE";
    public static final String EXTRA_ID = "EXTRA_ID";

    private EditText name_p;
    private EditText age_p;
    private EditText bill_p;
    private EditText disease_p;
    private Button delete_btn;

    PatientViewModel patientViewModel;

    int idUD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        name_p = findViewById(R.id.e_text_name);
        age_p = findViewById(R.id.e_text_age);
        bill_p = findViewById(R.id.e_text_bill);
        disease_p = findViewById(R.id.e_text_disease);
        delete_btn = findViewById(R.id.delete);

        Intent intent = getIntent();

        setTitle("Edit/Delete Patient");

        name_p.setText(intent.getStringExtra(EXTRA_NAME));
        age_p.setText(String.valueOf(intent.getIntExtra(EXTRA_AGE, 1)));
        bill_p.setText(String.valueOf(intent.getIntExtra(EXTRA_BILL, 1)));
        disease_p.setText(intent.getStringExtra(EXTRA_DISEASE));

        idUD = intent.getIntExtra(EXTRA_ID, 1);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                patientViewModel.delete(idUD);
                Intent intent = new Intent(EditPatient.this, PatientActivity.class);
                startActivity(intent);
                Toast.makeText(EditPatient.this, "Patient Deleted!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void updatePatient() {
        String name = name_p.getText().toString();
        String age = age_p.getText().toString();
        String bill = bill_p.getText().toString();
        String disease = disease_p.getText().toString();

        if (name.trim().isEmpty() || age.trim().isEmpty() || bill.trim().isEmpty() || disease.trim().isEmpty()) {
            Toast.makeText(this, "Please insert complete information.", Toast.LENGTH_SHORT).show();
        } else {
            int ageNew = Integer.parseInt(age);
            int billNew = Integer.parseInt(bill);

            patientViewModel.update(idUD, name, ageNew, disease, billNew);

            Intent intent = new Intent(EditPatient.this, PatientActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.update_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.update_patient:
                updatePatient();
                Toast.makeText(this, "Patient Updated!", Toast.LENGTH_SHORT).show();
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