package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylaundry.Model.User;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LaundryMyAccount extends AppCompatActivity {
    TextView userName , email , activeOrder , totalOrder;
    EditText fullName , emailId , password , phoneNumber;
    DatabaseReference databaseReference , databaseReference1 , databaseReference2;
    User userData = new User();
    FirebaseDatabase database ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_my_account);

        userName = findViewById(R.id.laundry_user_name);
        email = findViewById(R.id.laundry_email_id);
        fullName  = findViewById(R.id.laundry_full_name);
        emailId = findViewById(R.id.laundry_email);
        password  = findViewById(R.id.laundry_password);
        phoneNumber = findViewById(R.id.laundry_phone_number);
        activeOrder = findViewById(R.id.laundry_active_order_number);
        totalOrder = findViewById(R.id.order_history_number);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users").child(currentUser.getUid());

        databaseReference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            userData = snapshot.getValue(User.class);
                            userName.setText(Objects.requireNonNull(userData).getFullName());
                            email.setText(userData.getEmail());
                            fullName.setText(userData.getFullName());
                            emailId.setText(userData.getEmail());
                            password.setText(userData.getPassword());
                            phoneNumber.setText(userData.getPhoneNumber());

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LaundryMyAccount.this , "Unable to fetch the Data" , Toast.LENGTH_LONG).show();

                    }
                });


        final long[] active_order = {0};
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Laundry-Active-Order").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    active_order[0] = snapshot.getChildrenCount();



                }
                activeOrder.setText(String.valueOf(active_order[0]));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final long[] total_order = {0};
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Laundry-Order-History").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    total_order[0] = snapshot.getChildrenCount();


                }
                totalOrder.setText(String.valueOf(total_order[0]));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}