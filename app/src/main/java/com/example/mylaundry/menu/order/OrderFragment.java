package com.example.mylaundry.menu.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylaundry.R;
import com.example.mylaundry.menu.home.homeModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {
    private  String phone;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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

    RecyclerView recyclerView;
    DatabaseReference mRef;
    OrderAdapter orderAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        phone = getArguments().getString("phoneNumber");
        mRef = FirebaseDatabase.getInstance().getReference().child("Customer-Order-History").child(phone);
        recyclerView = view.findViewById(R.id.recycler_order_history);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<OrderModel> options
                = new FirebaseRecyclerOptions.Builder<OrderModel>()
                .setQuery(mRef, OrderModel.class)
                .build();

        orderAdapter = new OrderAdapter(options);
        recyclerView.setAdapter(orderAdapter);


        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        orderAdapter.startListening();
    }

    @Override public void onStop()
    {
        super.onStop();
        orderAdapter.stopListening();
    }


}