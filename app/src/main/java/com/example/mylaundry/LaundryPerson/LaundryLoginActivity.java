package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylaundry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LaundryLoginActivity extends AppCompatActivity {
    private EditText Login_email , Login_password;
    ImageView image;
    TextView logo , sloganText;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_login);




        TextView register = (TextView) findViewById(R.id.laundry_registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LaundryLoginActivity.this, LaundryRegistrationActivity.class);


                startActivity(registerIntent);

            }
        });



        Button login = (Button) findViewById(R.id.laundry_loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


        Login_email = (EditText) findViewById(R.id.laundry_login_email);
        Login_password = (EditText) findViewById(R.id.laundry_login_password);


        mAuth = FirebaseAuth.getInstance();


        loadingBar = new ProgressDialog(this);

    }
    /*

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currenUser = mAuth.getCurrentUser();

        if(currenUser != null){

            sendUserToMainActivity();
        }


    }

     */
    private void userLogin(){
        String email = Login_email.getText().toString().trim();
        String password = Login_password.getText().toString().trim();
        if(email.isEmpty()){
            Login_email.setError("Enter Email");
            Login_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            Login_password.setError("Enter the Password");
            Login_password.requestFocus();
            return;
        }

        loadingBar.setTitle("Logging into Account");
        loadingBar.setMessage("Please wait,while we are Logging into your Account..");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendUserToMainActivity();
                    Toast.makeText(LaundryLoginActivity.this, "you are Logged in Successfully!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();



                }
                else{
                    String message  = Objects.requireNonNull(task.getException()).toString().trim();
                    Toast.makeText(LaundryLoginActivity.this  , "Attention : " + message , Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void sendUserToMainActivity() {
        Intent mainIntent = new Intent(LaundryLoginActivity.this, LaundryMainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();


    }
}