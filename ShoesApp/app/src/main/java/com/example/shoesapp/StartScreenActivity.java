package com.example.shoesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class StartScreenActivity extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    ImageView img;
    TextView txt1, txt2;
    private static int splash_sreen = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img = findViewById(R.id.logoStart);
        txt1 = findViewById(R.id.txt1Start);
        txt2 = findViewById(R.id.txt2Start);

        img.setAnimation(topAnim);
        txt1.setAnimation(bottomAnim);
        txt2.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent callMainAcc = new Intent(getApplicationContext(), MainActivity.class);
                callMainAcc.putExtra("co", "Y");
                callMainAcc.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(callMainAcc);
                finish();
            }
        },splash_sreen);


    }
}
