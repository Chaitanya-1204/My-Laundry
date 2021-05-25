package com.example.mylaundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mylaundry.LaundryPerson.LaundryMyAccount;
import com.example.mylaundry.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {
    TextView userName , email;
    EditText fullName , emailId , password , phoneNumber;
    DatabaseReference databaseReference;
    User userData = new User();
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        userName = findViewById(R.id.user_name);
        email = findViewById(R.id.email_id);
        fullName  = findViewById(R.id.profile_full_name);
        emailId = findViewById(R.id.profile_email_id);
        password  = findViewById(R.id.profile_password);
        phoneNumber = findViewById(R.id.profile_phone_number);




        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userData = snapshot.getValue(User.class);
                        userName.setText(userData.getFullName());
                        email.setText(userData.getEmail());
                        fullName.setText(userData.getFullName());
                        emailId.setText(userData.getEmail());
                        password.setText(userData.getPassword());
                        phoneNumber.setText(userData.getPhoneNumber());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MyAccount.this , "Unable to fetch the Data" , Toast.LENGTH_LONG).show();

                    }
                });


    }




}