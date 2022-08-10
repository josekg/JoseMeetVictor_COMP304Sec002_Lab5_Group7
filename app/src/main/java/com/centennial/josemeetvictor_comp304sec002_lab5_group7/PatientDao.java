package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientDao {

    private static volatile PatientDao INSTANCE;
    private static final String DATABASE_NAME = "PatientDB";
    FirebaseDatabase database;
    DatabaseReference myRef;

    //Singleton Pattern to have one instance of DB

    private PatientDao()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(DATABASE_NAME);
    }

    public static PatientDao getInstance(){
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = new PatientDao();
        }
        return INSTANCE;
    }

    public void insert(Patient patient){
        myRef.push().setValue(patient);
    }

    public void delete(int id) {

        Query deleteQuery = myRef.orderByChild("id").equalTo(id);

        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    snapshot1.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void update(int iD, String name, int ageNew, String disease, int billNew) {

        Query updateRef = myRef.orderByChild("id").equalTo(iD);
        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    Patient patient = new Patient(iD, name, ageNew, disease, billNew);
                    snapshot1.getRef().setValue(patient);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
