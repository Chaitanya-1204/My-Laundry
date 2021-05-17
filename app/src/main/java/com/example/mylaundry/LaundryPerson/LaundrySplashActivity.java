package com.example.mylaundry.LaundryPerson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylaundry.LoginActivity;
import com.example.mylaundry.R;
import com.example.mylaundry.SplashActivity;

public class LaundrySplashActivity extends AppCompatActivity {
    Animation topAnimation, bottomAnimation;
    ImageView imageView;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry_splash);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animations);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animations);


        imageView = findViewById(R.id.laundry_spalsh_image);
        logo = findViewById(R.id.laundry_splash_logo_text);
        slogan = findViewById(R.id.laundry_splash_slogan_text);


        imageView.setAnimation(topAnimation);
        logo.setAnimation(bottomAnimation);
        slogan.setAnimation(bottomAnimation);

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(LaundrySplashActivity.this, LaundryLoginActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(imageView , "splash_image");
                pairs[1] = new Pair<View , String>(logo , "splash_logo_text");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LaundrySplashActivity.this , pairs);
                startActivity(i , options.toBundle());
                finish();
            }
        }, 3000);


    }
}