package com.centennial.josemeetvictor_comp304sec002_lab5_group7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText nameET,emailET,passwordET,phoneET;
    Button register,login;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Register");

        nameET = (EditText) findViewById(R.id.nameET);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        phoneET = (EditText) findViewById(R.id.phoneET);

        register = (Button) findViewById(R.id.registerButtonRegisterPage);
        login = (Button) findViewById(R.id.loginButtonRegisterPage);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }
    }

    public void registerUser(View view) {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            emailET.setError("Email is necessary to log in.");
            return;
        }
        if(TextUtils.isEmpty(password)){
            passwordET.setError("Email is necessary to log in.");
            return;
        }
        if(password.length() < 6){
            passwordET.setError("Password length cannot be less than 6 characters.");
            return;
        }

        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registration successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }else{
                    Toast.makeText(Register.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void goToLoginPage(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}