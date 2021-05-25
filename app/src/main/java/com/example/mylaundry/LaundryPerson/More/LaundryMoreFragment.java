package com.example.mylaundry.LaundryPerson.More;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mylaundry.LaundryPerson.LaundryContactUs;
import com.example.mylaundry.LaundryPerson.LaundryFaq;
import com.example.mylaundry.LaundryPerson.LaundryMyAccount;
import com.example.mylaundry.LoginActivity;
import com.example.mylaundry.R;
import com.google.firebase.auth.FirebaseAuth;

public class LaundryMoreFragment extends Fragment {
    private LinearLayout myAccount , signOut,Faq,ContactUs;
    private ProgressDialog loadingBar;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public LaundryMoreFragment() {
        // Required empty public constructor
    }



    public static LaundryMoreFragment newInstance(String param1, String param2) {
        LaundryMoreFragment fragment = new LaundryMoreFragment();
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
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_laundry_more, container, false);
        myAccount = (LinearLayout) view.findViewById(R.id.laundry_my_account);
        myAccount.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , LaundryMyAccount.class));
            }
        }));



        ContactUs = (LinearLayout) view.findViewById(R.id.laundry_contactUs);
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity() , LaundryContactUs.class));


            }
        });

        Faq = (LinearLayout) view.findViewById(R.id.laundry_faq);
        Faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity() , LaundryFaq.class));




            }
        });
        signOut = (LinearLayout) view.findViewById(R.id.laundry_signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity() , LoginActivity.class));

                Toast.makeText(getActivity() , "Logged Out" , Toast.LENGTH_LONG).show();
                getActivity().finish();


            }
        });




        return view;
    }
}