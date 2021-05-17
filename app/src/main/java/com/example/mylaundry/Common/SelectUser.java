package com.example.mylaundry.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mylaundry.LaundryPerson.LaundrySplashActivity;
import com.example.mylaundry.R;
import com.example.mylaundry.SplashActivity;

public class SelectUser extends AppCompatActivity {
    String prevStarted = "yes";
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            // moveToSecondary();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        Button button2 =findViewById(R.id.loginLaundry);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SelectUser.this, LaundrySplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button button =findViewById(R.id.loginCustomer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SelectUser.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void moveToSecondary(){


        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}