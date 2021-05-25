package com.example.mylaundry.LaundryPerson.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mylaundry.LaundryPerson.AddOrder;
import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LuandryHomeFragment1 extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    LaundryActiveOrderAdapter orderAdapter;
    DatabaseReference databaseReference;
    private Button addOrder;

    public LuandryHomeFragment1() {
        // Required empty public constructor
    }


    public static LuandryHomeFragment1 newInstance(String param1, String param2) {
        LuandryHomeFragment1 fragment = new LuandryHomeFragment1();
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
        View view = inflater.inflate(R.layout.fragment_luandry_home1, container, false);
        recyclerView = view.findViewById(R.id.laundry_recycler_active_order);
        databaseReference = FirebaseDatabase.getInstance().getReference("Laundry-Active-Order").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<activeOrder> options
                = new FirebaseRecyclerOptions.Builder<activeOrder>()
                .setQuery(databaseReference, activeOrder.class)
                .build();
        orderAdapter = new LaundryActiveOrderAdapter(options);
        recyclerView.setAdapter(orderAdapter);
        addOrder = view.findViewById(R.id.laundry_button2);
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , AddOrder.class));

            }
        });




        return  view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        orderAdapter.startListening();
    }
    @Override
    public void onStop()
    {
        super.onStop();
        orderAdapter.stopListening();
    }
}