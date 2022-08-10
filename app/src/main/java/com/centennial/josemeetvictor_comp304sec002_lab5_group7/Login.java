package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailET, passwordET;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        emailET = (EditText)  findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);

        fAuth = FirebaseAuth.getInstance();


    }

    public void loginUser(View view) {

        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"Login successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),PatientActivity.class));
                }else{
                    Toast.makeText(Login.this,"Incorrect email or password!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void goToRegistrationPage(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }

}