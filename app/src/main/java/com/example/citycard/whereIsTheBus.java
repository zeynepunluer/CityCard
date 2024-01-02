package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class whereIsTheBus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_is_the_bus);

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