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
import android.widget.Toast;

import com.example.mylaundry.LaundryPerson.AddOrder;
import com.example.mylaundry.R;

import java.util.ArrayList;
import java.util.List;


public class LaundryHomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    public Button button2;


    public LaundryHomeFragment() {
        // Required empty public constructor
    }

    public static LaundryHomeFragment newInstance(String param1, String param2) {
        LaundryHomeFragment fragment = new LaundryHomeFragment();
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
    List <LaundryHomeModel> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laundry_home, container, false);
        recyclerView = view.findViewById(R.id.laundry_recycler_active_order);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setNestedScrollingEnabled(true);

        // initializeData();

        button2=view.findViewById(R.id.laundry_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), AddOrder.class);
                startActivity(intent);
                Toast.makeText(getActivity() , "Add Order" , Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(new LaundryHomeAdapter(initializeData()));
        return view;
    }


    private List<LaundryHomeModel> initializeData() {

        orderList = new ArrayList<>();

        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "1" , "$10"));
        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "2" , "$10" ));
        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "3" , "$10" ));
        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "4" , "$10"));
        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "5" , "$10"));
        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "6" , "$10"));
        orderList.add(new LaundryHomeModel(R.drawable.bg_post1 , "7" , "$10"));

        return orderList;



    }
}