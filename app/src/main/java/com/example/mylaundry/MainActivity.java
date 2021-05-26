package com.example.mylaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mylaundry.menu.home.HomeFragment;
import com.example.mylaundry.menu.more.MoreFragment;
import com.example.mylaundry.menu.order.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private  String phone;
    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phoneNumber");


        bundle  =new Bundle();
        bundle.putString("phoneNumber" , phone);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();



        /*

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        toggle.syncState();

         */





    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {

        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_menu_home:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);
                selectedFragment = homeFragment;
                break;
            case R.id.nav_menu_orders:
                OrderFragment orderFragment = new OrderFragment();
                orderFragment.setArguments(bundle);
                selectedFragment = orderFragment;
                break;
            case R.id.nav_menu_more:
                selectedFragment = new MoreFragment();
                break;

        }


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    };

   
}