package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HowToGo extends AppCompatActivity implements CustomFragmentListener {
    public void onCustomFragmentClose() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_go);
        CardView cardView2 = findViewById(R.id.cardView2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout hiddenLayout = findViewById(R.id.hiddenLayout);
                if (hiddenLayout.getVisibility() == View.GONE) {
                    hiddenLayout.setVisibility(View.VISIBLE);
                } else {
                    hiddenLayout.setVisibility(View.GONE);
                }
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

        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);

        // Set OnClickListener for each CardView
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment("Art Museum");
                setAllCardViewsInvisible();
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment("Theme Park");
                setAllCardViewsInvisible();
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment("5th Avenue");
                setAllCardViewsInvisible();
            }
        });


    }


    private void openCustomFragment(String routeInfo) {
        CustomFragment customFragment = CustomFragment.newInstance(routeInfo);

        // CustomFragment'ı açmadan önce fragment içeriğini özelleştirme
        // customFragment.setCardContent(content); // Bu satır artık gerekli değil

        // Fragment transaction başlat
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, customFragment)
                .addToBackStack(null)
                .commit();
    }

    private void setAllCardViewsInvisible() {
        findViewById(R.id.cardView2).setVisibility(View.INVISIBLE);
        findViewById(R.id.cardView3).setVisibility(View.INVISIBLE);
        findViewById(R.id.cardView4).setVisibility(View.INVISIBLE);
        findViewById(R.id.cardView5).setVisibility(View.INVISIBLE);
    }
    public void setAllCardViewsVisible() {
        findViewById(R.id.cardView2).setVisibility(View.VISIBLE);
        findViewById(R.id.cardView3).setVisibility(View.VISIBLE);
        findViewById(R.id.cardView4).setVisibility(View.VISIBLE);
        findViewById(R.id.cardView5).setVisibility(View.VISIBLE);

    }
}