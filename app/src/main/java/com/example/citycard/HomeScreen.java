package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity { //This is the main page of the application
                                                //here we have buttons that lead user to other activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button btnOnlineCharging = findViewById(R.id.btnCharging); //button for navigating user to online charging activity
        btnOnlineCharging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, OnlineCharging.class);
                startActivity(intent);
            }
        });

        Button btnRouteSchedule = findViewById(R.id.btnSchedule);  //button for navigating user to route schedule activity
        btnRouteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, RouteSchedule.class);
                startActivity(intent);
            }
        });

        Button btnWhereIs = findViewById(R.id.btnWhere);
        btnWhereIs.setOnClickListener(new View.OnClickListener() {   //button for navigating user to where is the bus activity
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, whereIsTheBus.class);
                startActivity(intent);
            }
        });

        Button btnHowToGo = findViewById(R.id.btnHowtogo);          //button for navigating user to how to go activity
        btnHowToGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, HowToGo.class);
                startActivity(intent);
            }
        });





    }
}