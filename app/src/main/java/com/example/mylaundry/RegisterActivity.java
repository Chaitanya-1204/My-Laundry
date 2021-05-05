package com.example.mylaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Login ,banner , registerUser ;
    private EditText FullName , Phone , Email , Password;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Login = (TextView) findViewById(R.id.Login);
        Login.setOnClickListener(this);
        
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        FullName = (EditText) findViewById(R.id.fullName);
        Phone = (EditText) findViewById(R.id.phoneNumber);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);



        mAuth = FirebaseAuth.getInstance();




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login:
                startActivity(new Intent(this , LoginActivity.class));
                break;
            case R.id.banner:
                startActivity(new Intent(this , LoginActivity.class));
                break;
            case R.id.registerUser:
                registeredUser();
                break;
        }

    }

    private void registeredUser() {
        String email = Email.getText().toString().trim();
        String fullName = FullName.getText().toString().trim();
        String password = Password.getText().toString().trim();
        String phone_number = Phone.getText().toString().trim();

        if(fullName.isEmpty()){
            FullName.setError("Name is Required");
            FullName.requestFocus();
            return;
        }
        if(phone_number.isEmpty()){
            Phone.setError("Phone Number is required is Required");
            Phone.requestFocus();
            return;
        }
        if(phone_number.length() != 10){
            Phone.setError("Invalid Phone Number");
            Phone.requestFocus();
            return;
        }
        if(email.isEmpty()){
            Email.setError("Email is Required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Enter a valid Mail");
            Email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Password.setError("Password is Required");
            Password.requestFocus();
            return;
        }

        if (password.length() < 6){
            Password.setError("Password must be 6 or more Character Long");
            Password.requestFocus();
            return;

        }
        mAuth.createUserWithEmailAndPassword(email , password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this , "User Registered Succesfully" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this , "Failed to Register" , Toast.LENGTH_LONG).show();
                        }
                    }
                });










    }
}