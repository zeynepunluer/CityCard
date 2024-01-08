package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HowToGo extends AppCompatActivity{
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

        CardView toolBarCardView = findViewById(R.id.toolBar);


        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);


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
        HowToGoFragment customFragment = HowToGoFragment.newInstance(routeInfo);
        customFragment.setParentActivity(this); // this, HowToGo sınıfının bir örneği olduğunu belirtir

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