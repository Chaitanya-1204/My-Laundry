package com.example.mylaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylaundry.LaundryPerson.LaundryMainActivity;
import com.example.mylaundry.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText Login_email , Login_password;
    ImageView image;
    TextView logo , sloganText;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    User userData = new User();
    String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_login);



        TextView register = (TextView) findViewById(R.id.registerButton);
        register.setOnClickListener(this);



        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(this);


        Login_email = (EditText) findViewById(R.id.login_email);
        Login_password = (EditText) findViewById(R.id.login_password);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");



        loadingBar = new ProgressDialog(this);
        


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currenUser = mAuth.getCurrentUser();

        if(currenUser != null){

            sendUserToMainActivity();
        }


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);



                startActivity(registerIntent);


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
        loadingBar.setTitle("Logging into Account");
        loadingBar.setMessage("Please wait,while we are Logging into your Account..");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendUserToMainActivity();
                    Toast.makeText(LoginActivity.this, "you are Loggedin Successfully!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();



                }
                else{
                    String message  = Objects.requireNonNull(task.getException()).toString().trim();
                    Toast.makeText(LoginActivity.this  , "Attention : " + message , Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendUserToMainActivity() {
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                         userData = snapshot.getValue(User.class);

                        Intent mainIntent;


                        if(userData.getRole().equals("Customer")){



                            mainIntent = new Intent(LoginActivity.this, MainActivity.class);


                        }
                         else{

                            mainIntent = new Intent(LoginActivity.this, LaundryMainActivity.class);

                        }
                        mainIntent.putExtra("phoneNumber" ,userData.getPhoneNumber() );
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LoginActivity.this , "Unable to Login" , Toast.LENGTH_LONG).show();

                    }
                });
        



    }
}