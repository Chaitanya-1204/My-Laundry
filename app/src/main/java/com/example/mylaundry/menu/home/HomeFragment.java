package com.example.mylaundry.menu.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylaundry.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private  String phone;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private RecyclerView recyclerView;
    homeAdapter HomeAdapter;
    DatabaseReference mRef;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        phone = getArguments().getString("phoneNumber");

        mRef = FirebaseDatabase.getInstance().getReference().child("Customer-Active-Order").child(phone);
        recyclerView   = view.findViewById(R.id.recycler_active_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<homeModel> options
                = new FirebaseRecyclerOptions.Builder<homeModel>()
                .setQuery(mRef, homeModel.class)
                .build();

        HomeAdapter = new homeAdapter(options);
        recyclerView.setAdapter(HomeAdapter);

        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        HomeAdapter.startListening();
    }

    @Override public void onStop()
    {
        super.onStop();
        HomeAdapter.stopListening();
    }
}