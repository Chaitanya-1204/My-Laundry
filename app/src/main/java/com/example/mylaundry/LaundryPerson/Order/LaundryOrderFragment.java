package com.example.mylaundry.LaundryPerson.Order;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mylaundry.LaundryPerson.Home.LaundryActiveOrderAdapter;
import com.example.mylaundry.LaundryPerson.Home.activeOrder;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LaundryOrderFragment extends Fragment {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    LaundryOrderHistoryAdapter orderHistoryAdapter;




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public LaundryOrderFragment() {
        // Required empty public constructor

    }



    public static LaundryOrderFragment newInstance(String param1, String param2) {
        LaundryOrderFragment fragment = new LaundryOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laundry_order, container, false);
        recyclerView = view.findViewById(R.id.laundry_recycler_order_history);
        databaseReference = FirebaseDatabase.getInstance().getReference("Laundry-Order-History").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<orderHistory> options
                = new FirebaseRecyclerOptions.Builder<orderHistory>()
                .setQuery(databaseReference, orderHistory.class)
                .build();
        orderHistoryAdapter = new LaundryOrderHistoryAdapter(options);
         recyclerView.setAdapter(orderHistoryAdapter);



        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();


         orderHistoryAdapter.startListening();
    }
    @Override
    public void onStop()
    {
        super.onStop();

        orderHistoryAdapter.stopListening();
    }



}