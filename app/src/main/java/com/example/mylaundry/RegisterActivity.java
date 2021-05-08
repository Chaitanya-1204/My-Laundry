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



}