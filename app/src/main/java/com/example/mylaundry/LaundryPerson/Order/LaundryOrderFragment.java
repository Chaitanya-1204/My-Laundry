package com.example.mylaundry.LaundryPerson.Order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylaundry.R;

import java.util.ArrayList;
import java.util.List;


public class LaundryOrderFragment extends Fragment {


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
    RecyclerView recyclerView;
    List<LaundryOrderModel> orderHistoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laundry_order, container, false);
        recyclerView = view.findViewById(R.id.laundry_recycler_order_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setNestedScrollingEnabled(true);

        // initializeData();
        recyclerView.setAdapter(new LaundryOrderAdapter(initializeData()));
        return view;
    }
    private List<LaundryOrderModel> initializeData() {
        orderHistoryList = new ArrayList<>();


        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));

        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));

        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));

        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));

        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));

        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));
        orderHistoryList.add(new LaundryOrderModel(R.drawable.bg_post1 , "1" , "$10" ));


        return orderHistoryList;
    }
}