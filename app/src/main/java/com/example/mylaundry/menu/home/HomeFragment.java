package com.example.mylaundry.menu.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylaundry.R;
import com.example.mylaundry.menu.home.HomeModel;

import java.util.ArrayList;
import java.util.List;


public class    HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }



    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    List<HomeModel> orderList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_active_order);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setNestedScrollingEnabled(true);
        
       // initializeData();
        recyclerView.setAdapter(new HomeAdapter(initializeData()));
        return view;
    }

    private List<HomeModel> initializeData() {

        orderList = new ArrayList<>();

        orderList.add(new HomeModel(R.drawable.bg_post1 , "1" , "$10" , "Washing"));
        orderList.add(new HomeModel(R.drawable.bg_post1 , "2" , "$10" , "Ironing"));
        orderList.add(new HomeModel(R.drawable.bg_post1 , "3" , "$10" , "Ready"));
        orderList.add(new HomeModel(R.drawable.bg_post1 , "4" , "$10" , "Dry Clean"));
        orderList.add(new HomeModel(R.drawable.bg_post1 , "5" , "$10" , "Ironing"));
        orderList.add(new HomeModel(R.drawable.bg_post1 , "6" , "$10" , "Ironing"));
        orderList.add(new HomeModel(R.drawable.bg_post1 , "7" , "$10" , "Ironing"));
        return orderList;



    }
}