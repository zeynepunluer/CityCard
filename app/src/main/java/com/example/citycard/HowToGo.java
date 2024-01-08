package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class HowToGo extends AppCompatActivity{
    public void onCustomFragmentClose() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_go);
        ArrayList<HTGDirections> directionsList = new ArrayList<>();
        directionsList.add(new HTGDirections("Art Museum", " asdsadasdasdsadasdsaLine 1.Line 2.Line 3."));
        directionsList.add(new HTGDirections("Theme Park", " Line 1.\nLine 2.\nLine 3."));
        directionsList.add(new HTGDirections("5th Avenue", " Line 1.\nLine 2.\nLine 3."));

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
                openCustomFragment(directionsList.get(0));
                setAllCardViewsInvisible();
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment(directionsList.get(1));
                setAllCardViewsInvisible();
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment(directionsList.get(2));
                setAllCardViewsInvisible();
            }
        });
    }


    private void openCustomFragment(HTGDirections directions) {
        HowToGoFragment customFragment = HowToGoFragment.newInstance(directions);
        customFragment.setParentActivity(this);

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