package com.example.mylaundry.LaundryPerson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mylaundry.LaundryPerson.Home.LuandryHomeFragment1;
import com.example.mylaundry.LaundryPerson.More.LaundryMoreFragment;
import com.example.mylaundry.LaundryPerson.Order.LaundryOrderFragment;
import com.example.mylaundry.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LaundryMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.laundry_bottom_navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.laundry_fragment_container, new LuandryHomeFragment1()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {

        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_menu_home:
                selectedFragment = new LuandryHomeFragment1();
                break;
            case R.id.nav_menu_orders:
                selectedFragment = new LaundryOrderFragment();
                break;
            case R.id.nav_menu_more:
                selectedFragment = new LaundryMoreFragment();
                break;

        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.laundry_fragment_container, selectedFragment)
                .commit();
        return true;
    };

}