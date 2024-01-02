package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnlineCharging extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_charging);

        Button btnBalanceCheck = (Button) findViewById(R.id.btnBalanceCheck);
        Dialog dialog = new Dialog(this);
        btnBalanceCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popup_balancecheck);
                dialog.show();
            }

        });

        Button btnTopUp = (Button) findViewById(R.id.btnTopUp);
        btnTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.popup_topup);
                dialog.show();
            }

        });

        // Find the CardView by its ID
        CardView toolBarCardView = findViewById(R.id.toolBar);

        // Set an OnClickListener for the CardView
        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event here
                // In this example, navigate back to the home screen
                onBackPressed();
            }
        });


    }





}