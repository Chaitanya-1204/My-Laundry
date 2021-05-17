package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylaundry.Model.LaundryPerson;
import com.example.mylaundry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class LaundryRegistrationActivity extends AppCompatActivity {
    private EditText FullName , Phone , Email , Password;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    LaundryPerson userInfo = new LaundryPerson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_registration);
        TextView login = (TextView) findViewById(R.id.laundry_Login);



        Button registerUser = (Button) findViewById(R.id.laundry_registerUser);


        FullName = (EditText) findViewById(R.id.laundry_register_fullName);
        Phone = (EditText) findViewById(R.id.laundry_register_phoneNumber);
        Email = (EditText) findViewById(R.id.laundry_register_email);
        Password = (EditText) findViewById(R.id.laundry_register_password);
        loadingBar = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        databaseReference = dataBase.getReference("Laundry Person");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LaundryRegistrationActivity.this , LaundryLoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);

            }
        });
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registereUser();

            }
        });
    }

    private void registereUser() {
        String email = Email.getText().toString();
        String fullName = FullName.getText().toString();
        String password = Password.getText().toString();
        String phone_number = Phone.getText().toString();

        if(!validateName() ||  !validateEmail() || !validatePhoneNumber() || !validatePassword()){
            return;
        }


        loadingBar.setTitle("Creating New Account");
        loadingBar.setMessage("Please wait,while we are creating your new Account..");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);
        mAuth.createUserWithEmailAndPassword(email , password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userInfo.setEmail(email);
                            userInfo.setPhoneNumber(phone_number);
                            userInfo.setFullName(fullName);
                            userInfo.setPassword(password);
                            databaseReference
                                    .child(phone_number)
                                    .setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(LaundryRegistrationActivity.this , LaundryMainActivity.class));
                                        finish();

                                        Toast.makeText(LaundryRegistrationActivity.this , "User Registered!!" , Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();

                                    }
                                    else{
                                        Toast.makeText(LaundryRegistrationActivity.this , "Failed to register! Try Again" , Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });

                        }
                        else {
                            Toast.makeText(LaundryRegistrationActivity.this , "Failed To Register" , Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }
                    }
                });
    }
    private Boolean validateName(){

        String val = FullName.getText().toString().trim();
        if(val.isEmpty()){
            FullName.setError("Field Cannot be Empty");
            return false;

        }
        else{
            FullName.setError(null);
            return true;
        }

    }
    private Boolean validateEmail(){

        String val = Email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9+_. -]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            Email.setError("Field Cannot be Empty");
            return false;

        }
        else if(!val.matches(emailPattern)){
            Email.setError("Invalid Email Address! ");
            return false;


        }
        else{
            Email.setError(null);

            return true;
        }

    }
    private Boolean validatePhoneNumber(){

        String val = Phone.getText().toString().trim();
        if(val.isEmpty()){
            Phone.setError("Field Cannot be Empty");
            return false;

        }
        else{
            Phone.setError(null);
            return true;
        }

    }
    private Boolean validatePassword(){

        String val = Password.getText().toString();

        String digit = "^(?=.*[0-9])";
        Pattern digitR = Pattern.compile(digit);
        String lowerCase = "(?=.*[a-z])";
        Pattern lowerCaseR = Pattern.compile(lowerCase);
        String upperCase = "(?=.*[A-Z])";
        Pattern upperCaseR = Pattern.compile(upperCase);
        String whiteSpace = "(?=\\S+$)";
        Pattern whiteSpaceR = Pattern.compile(whiteSpace);
        String length = ".{8,20}";
        Pattern lengthR = Pattern.compile(length);
        String specialCharacter = "(?=.*[@#$%^&+=])";
        Pattern specialCharacterR = Pattern.compile(specialCharacter);
        if(val.isEmpty()){
            Password.setError("Field Cannot be Empty");
            return false;

        }

        else if(digitR.matcher(val).matches()){

            Password.setError("Password should contain at least 1 digit");
            return false;
        }
        else if(lowerCaseR.matcher(val).matches()){
            Password.setError("Password should contain at least 1 lowercase Character");
            return false;
        }
        else if(upperCaseR.matcher(val).matches()){
            Password.setError("Password should contain at least 1 uppercase Character");
            return false;
        }
        else if(!lengthR.matcher(val).matches()){
            Password.setError("Password should be of length of 8 or more Characters");
            return false;
        }
        else if(whiteSpaceR.matcher(val).matches()){
            Password.setError("Password should not contain White Space");
            return false;
        }
        else if(specialCharacterR.matcher(val).matches()){
            Password.setError("Password should contain at least 1 Special Character");
            return false;
        }

        else{

            Password.setError(null);


            return true;
        }


    }

}