package com.example.mylaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylaundry.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;
import java.util.regex.Pattern;

import com.example.mylaundry.Model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText FullName , Phone , Email , Password;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    User userInfo = new User();



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView login = (TextView) findViewById(R.id.Login);
        login.setOnClickListener(this);


        Button registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        FullName = (EditText) findViewById(R.id.register_fullName);
        Phone = (EditText) findViewById(R.id.register_phoneNumber);
        Email = (EditText) findViewById(R.id.register_email);
        Password = (EditText) findViewById(R.id.register_password);
        loadingBar = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase dataBase = FirebaseDatabase.getInstance();
        databaseReference = dataBase.getReference("Users");
        userInfo = new User();





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Login:
                Intent loginIntent = new Intent(this , LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
                break;

            case R.id.registerUser:
                registereUser();
                break;
        }

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
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(userInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(RegisterActivity.this , "User Registered!!" , Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();

                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this , "Failed to register! Try Again" , Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });

                        }
                        else {
                            Toast.makeText(RegisterActivity.this , "Failed To Register" , Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }
                    }
                });



        // addDataToFirebase(fullName , phone_number  , email);











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