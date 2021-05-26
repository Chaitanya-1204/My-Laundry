package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.mylaundry.LaundryPerson.Home.LuandryHomeFragment1;
import com.example.mylaundry.Model.User;
import com.example.mylaundry.Model.addOrderData;
import com.example.mylaundry.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Random;

public class AddOrder extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference , databaseReference1 , databaseReference2;
    EditText customerName , phoneNumber , shirts , pants , bedSheets , extras;
    Button placeOrder;
    addOrderData orderData;
    User userData = new User();




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
        databaseReference2 = database.getReference("Users");
        databaseReference = database.getReference("Laundry-Active-Order");
        databaseReference1 = database.getReference("Customer-Active-Order");

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer = customerName.getText().toString().trim();
                String phone = phoneNumber.getText().toString().trim();
                String shirt , extra , pant , bedsheet;
                if(shirts.getText().toString().isEmpty()){
                    shirt = "0";
                }
                else{

                    shirt = shirts.getText().toString().trim();
                }

                if(pants.getText().toString().isEmpty()){
                    pant = "0";
                }
                else{

                    pant = pants.getText().toString().trim();
                }


                if(extras.getText().toString().isEmpty()){
                    extra = "0";
                }
                else{
                    extra = extras.getText().toString().trim();
                }
                if(bedSheets.getText().toString().isEmpty()){
                    bedsheet = "0";
                }
                else{
                    bedsheet = bedSheets.getText().toString().trim();
                }




                int shirtCount = Integer.parseInt(shirt);
                int pantCount = Integer.parseInt(pant);
                int extraCount = Integer.parseInt(extra);
                int bedSheetCount = Integer.parseInt(bedsheet);
                int price = shirtCount * 10 + pantCount * 5 + extraCount * 5 + bedSheetCount * 20;
                int itemCount = shirtCount + pantCount + extraCount + bedSheetCount;
                String itemCounts = String.valueOf(itemCount);
                String Price = String.valueOf(price);








                orderData.setCustomerName(customer);
                orderData.setPhoneNumber(phone);
                orderData.setShirts(shirt);
                orderData.setPants(pant);
                orderData.setExtra(extra);
                orderData.setBedSheet(bedsheet);
                orderData.setImageUrl("https://image.flaticon.com/icons/png/512/411/411768.png");
                orderData.setPrice(Price);
                orderData.setItemCount(itemCounts);





                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(phone)
                        .setValue(orderData);

                databaseReference1.child(phone)
                        .child(phone)
                        .setValue(orderData);



                Toast.makeText(AddOrder.this, "Placed order", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddOrder.this , LaundryMainActivity.class));
                finish();





























            }
        });

        orderData = new addOrderData();





    }
}