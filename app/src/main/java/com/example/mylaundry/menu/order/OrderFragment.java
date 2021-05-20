package com.example.mylaundry.menu.order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylaundry.R;
import com.example.mylaundry.menu.home.HomeAdapter;
import com.example.mylaundry.menu.home.HomeModel;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {


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
    List<OrderModel> orderHistoryList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = view.findViewById(R.id.recycler_order_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setNestedScrollingEnabled(true);

        // initializeData();
        recyclerView.setAdapter(new OrderAdapter(initializeData()));
        return view;
    }

    private List<OrderModel> initializeData() {
        orderHistoryList = new ArrayList<>();


        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));
        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));
        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));
        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));
        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));
        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));
        orderHistoryList.add(new OrderModel(R.drawable.bg_post1 , "1" , "$10"));

        return orderHistoryList;
    }
}