package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeScreen extends AppCompatActivity { //This is the welcome screen that disappears after few seconds
    private static int splash_time= 3000;    //we manage this disappering process with splash

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        new Handler().postDelayed(new Runnable() {   //transition to the homescreen activity
            @Override
            public void run() {
                Intent homeIntent = new Intent(WelcomeScreen.this, HomeScreen.class);
                startActivity(homeIntent);
                finish();    //this is for preventing going back to the splash screen
            }
        },splash_time);  //setting the delay duration
    }
}