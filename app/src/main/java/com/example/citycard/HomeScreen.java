package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button btnOnlineCharging = findViewById(R.id.btnCharging);
        btnOnlineCharging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, OnlineCharging.class);
                startActivity(intent);
            }
        });

        Button btnRouteSchedule = findViewById(R.id.btnSchedule);
        btnRouteSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, RouteSchedule.class);
                startActivity(intent);
            }
        });

        Button btnWhereIs = findViewById(R.id.btnWhere);
        btnWhereIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, whereIsTheBus.class);
                startActivity(intent);
            }
        });

        Button btnHowToGo = findViewById(R.id.btnHowtogo);
        btnHowToGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, HowToGo.class);
                startActivity(intent);
            }
        });


    }
}