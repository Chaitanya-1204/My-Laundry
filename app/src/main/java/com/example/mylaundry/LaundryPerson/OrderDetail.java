package com.example.mylaundry.LaundryPerson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylaundry.LaundryPerson.Home.activeOrder;
import com.example.mylaundry.MainActivity;
import com.example.mylaundry.Model.addOrderData;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class OrderDetail extends AppCompatActivity {
    addOrderData orderData = new addOrderData();
    addOrderData completedOrder  = new addOrderData();
    DatabaseReference databaseReference , databaseReference1 , databaseReference2 , databaseReference3;
    TextView customerName , phoneNumber , shirt , pant , bedSheet , extra , price , itemcount;
    String phone;
    Button orderCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        customerName = findViewById(R.id.customer_name);
        phoneNumber = findViewById(R.id.Customer_phone_number);

        shirt = findViewById(R.id.customer_shirt);
        pant = findViewById(R.id.customer_pant);
        bedSheet = findViewById(R.id.customer_bedsheet);
        extra = findViewById(R.id.customer_extra);
        itemcount = findViewById(R.id.customer_itemCount);
        price= findViewById(R.id.customer_price);


        Intent intent = getIntent();
        phone = intent.getStringExtra("phoneNumber");

        databaseReference = FirebaseDatabase.getInstance().getReference("Laundry-Active-Order")
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












        orderCompleted = findViewById(R.id.order_completed);
        orderCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference1 = FirebaseDatabase.getInstance().getReference("Laundry-Order-History").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference2 = FirebaseDatabase.getInstance().getReference("Customer-Order-History").child(phone);
                databaseReference3  = FirebaseDatabase.getInstance().getReference("Customer-Active-Order").child(phone);
                databaseReference.child(phone).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            completedOrder = snapshot.getValue(addOrderData.class);
                            databaseReference1.child(completedOrder.getPhoneNumber()).setValue(completedOrder);
                            databaseReference2.child(completedOrder.getPhoneNumber()).setValue(completedOrder);
                            databaseReference3.child(phone).removeValue();


                            databaseReference.child(phone).removeValue();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                startActivity(new Intent(OrderDetail.this , LaundryMainActivity.class));
                Toast.makeText(OrderDetail.this, "Order Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        });











    }
}