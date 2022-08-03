package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientDao {

    private static volatile PatientDao INSTANCE;
    private static final String DATABASE_NAME = "PatientDB";
    FirebaseDatabase database;
    DatabaseReference myRef;

    //Singleton Pattern to have one instance of DB

    private PatientDao(){
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


}
