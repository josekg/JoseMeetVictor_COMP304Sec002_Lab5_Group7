package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Patient> patients;
    Query databaseReference;
    PatientAdapter patientAdapter;
    PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        FloatingActionButton btnAddPatient = findViewById(R.id.btn_add);
        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, AddPatient.class);
                startActivity(intent);
            }
        });

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        recyclerView = findViewById(R.id.recycler_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("PatientDB").orderByChild("id");
        patients = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        patientAdapter = new PatientAdapter(this, patients);
        recyclerView.setAdapter(patientAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Patient patient = dataSnapshot.getValue(Patient.class);
                    patients.add(patient);
                }
                patientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {

                Intent intent = new Intent(PatientActivity.this, EditPatient.class);
                intent.putExtra(EditPatient.EXTRA_ID, patient.getId());
                intent.putExtra(EditPatient.EXTRA_NAME, patient.getName());
                intent.putExtra(EditPatient.EXTRA_AGE, patient.getAge());
                intent.putExtra(EditPatient.EXTRA_BILL, patient.getBill());
                intent.putExtra(EditPatient.EXTRA_DISEASE, patient.getDisease());
                startActivity(intent);
            }
        });

        setTitle("Centennial Patients");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.signout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.signout:

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PatientActivity.this, MainActivity.class);
                Toast.makeText(this, "Sign out completed", Toast.LENGTH_SHORT).show();
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}

