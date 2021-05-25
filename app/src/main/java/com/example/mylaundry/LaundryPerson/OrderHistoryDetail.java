package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mylaundry.Model.addOrderData;
import com.example.mylaundry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class OrderHistoryDetail extends AppCompatActivity {
    addOrderData orderData = new addOrderData();

    DatabaseReference databaseReference , databaseReference1;
    TextView customerName , phoneNumber , shirt , pant , bedSheet , extra , price , itemcount;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);
        customerName = findViewById(R.id.history_customer_name);
        phoneNumber = findViewById(R.id.history_Customer_phone_number);

        shirt = findViewById(R.id.history_customer_shirt);
        pant = findViewById(R.id.history_customer_pant);
        bedSheet = findViewById(R.id.history_customer_bedsheet);
        extra = findViewById(R.id.history_customer_extra);
        itemcount = findViewById(R.id.history_customer_itemCount);
        price= findViewById(R.id.history_customer_price);



        Intent intent = getIntent();
        phone = intent.getStringExtra("phoneNumber");
        databaseReference = FirebaseDatabase.getInstance().getReference("Laundry-Order-History")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

        databaseReference.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    orderData = snapshot.getValue(addOrderData.class);
                    String customer = "Name of Customer: " + Objects.requireNonNull(orderData).getCustomerName();
                    String pants = "Number of Pants: " + orderData.getPants();
                    String Price = "Total Price: " + orderData.getPrice();
                    String Extra = "Number of Extras: " + orderData.getExtra();
                    String PhoneNumber = "Phone Number : " + orderData.getPhoneNumber();
                    String BedSheet = "Number of BedSheet : " + orderData.getBedSheet();
                    String ItemCount = "Total ItemCount : " + orderData.getItemCount();
                    String Shirts = "Number of Shirts : " + orderData.getShirts();
                    customerName.setText(customer);
                    pant.setText(pants);
                    price.setText(Price);
                    extra.setText(Extra);
                    phoneNumber.setText(PhoneNumber);
                    bedSheet.setText(BedSheet);
                    itemcount.setText(ItemCount);
                    shirt.setText(Shirts);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}