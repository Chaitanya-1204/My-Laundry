package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mylaundry.Model.addOrderData;
import com.example.mylaundry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddOrder extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText customerName , phoneNumber , shirts , pants , bedSheets , extras;
    Button placeOrder;
    addOrderData orderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        customerName = (EditText)findViewById(R.id.register_customerName);
        phoneNumber  =(EditText) findViewById(R.id.register_order_phoneNumber);
        shirts = (EditText)findViewById(R.id.shirt);
        pants  = (EditText)findViewById(R.id.pant);
        extras =(EditText) findViewById(R.id.extra);
        bedSheets = (EditText)findViewById(R.id.bedsheet);
        placeOrder = findViewById(R.id.placeOrder);






        database =FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Laundry Active Order");
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer = customerName.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String shirt = shirts.getText().toString().trim();
                String pant = pants.getText().toString().trim();
                String extra = extras.getText().toString().trim();
                String bedsheet = bedSheets.getText().toString().trim();
                Random r = new Random();
                int  orderId = r.nextInt(1000 + 1);
               
                String s = Integer.toString(orderId);


                orderData.setCustomerName(customer);
                orderData.setPhoneNumber(phone);
                orderData.setShirts(shirt);
                orderData.setPants(pant);
                orderData.setExtra(extra);
                orderData.setBedSheet(bedsheet);




                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(s)
                        .setValue(orderData);
                Toast.makeText(AddOrder.this , "Placed order" , Toast.LENGTH_LONG).show();



                /*
                databaseReference.child(phone)
                        .setValue(orderData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddOrder.this , "Placed Order" , Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(AddOrder.this , "Placed not  Order" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                 */


            }
        });

        orderData = new addOrderData();





    }
}