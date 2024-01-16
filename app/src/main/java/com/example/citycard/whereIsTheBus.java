package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class whereIsTheBus extends AppCompatActivity {  //this class represents the activity for tracking the bus location on the map

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_is_the_bus);


        CardView toolBarCardView = findViewById(R.id.toolBar);   // setting click listener for the toolbar card view to handle onBackPressed

        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        if (savedInstanceState == null) {  // initializing processes and creating intance if the activity is created for the first time

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


            MapsFragment mapsFragment = new MapsFragment();


            fragmentTransaction.add(R.id.fragment_container, mapsFragment);  //adding the instance we created to the fragment container


            fragmentTransaction.commit();
        }
    }
}