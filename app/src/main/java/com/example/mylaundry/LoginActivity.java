package com.example.mylaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Register;
    private EditText Login_email , Login_password;
    private Button Login;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Register = (TextView) findViewById(R.id.registerButton);
        Register.setOnClickListener(this);
        Login = (Button) findViewById(R.id.loginButton);
        Login.setOnClickListener(this);
        Login_email = (EditText) findViewById(R.id.login_email);
        Login_password = (EditText) findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();
        


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                startActivity(new Intent(this  , RegisterActivity.class));
                break;
            case R.id.loginButton:
                userLogin();
                break;

        }

    }

    private void userLogin() {
        String email = Login_email.getText().toString().trim();
        String password = Login_password.getText().toString().trim();
        if(email.isEmpty()){
            Login_email.setError("Enter Email");
            Login_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Login_email.setError("Enter a Valid Mail id");
            Login_email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Login_password.setError("Enter the Password");
            Login_password.requestFocus();
            return;
        }
        if(password.length() < 6){
            Login_password.setError("Password length should be 6 or more ");
            Login_password.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));

                }
                else{
                    Toast.makeText(LoginActivity.this  , "Failed to Login!! PLease check the Login Credentials" , Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}