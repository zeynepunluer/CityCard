package com.example.citycard;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteSchedule extends AppCompatActivity {
    public void onCustomFragmentClose() {
        getSupportFragmentManager().popBackStack();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_schedule);
        ArrayList<Schedules> scheduleList = new ArrayList<>();
        List<String> times = Arrays.asList("9.30","10.00","10.30","11.00","10.00","10.30","11.00","10.00","10.30","11.00","10.00","10.30","11.00","10.00","10.30","11.00","10.00","10.30","11.00");
        scheduleList.add(new Schedules("1-41",times,"Merkez","Avm"));
        scheduleList.add(new Schedules("1-1", times,"Okul","Muze"));
        scheduleList.add(new Schedules("1-39", times,"Ev","İŞ"));


        CardView toolBarCardView = findViewById(R.id.toolBar);

        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);
        CardView cardView6 = findViewById(R.id.cardView6);
        CardView cardView7 = findViewById(R.id.cardView7);


        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment(scheduleList.get(0));
                setAllCardViewsInvisible();
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment(scheduleList.get(1));
                setAllCardViewsInvisible();
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomFragment(scheduleList.get(2));
                setAllCardViewsInvisible();
            }
        });

    }


    private void openCustomFragment(Schedules schedule) {
        RouteFragment customFragment = RouteFragment.newInstance(schedule);
        customFragment.setParentActivity(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, customFragment)
                .addToBackStack(null)
                .commit();
    }

    private void setAllCardViewsInvisible() {
        int[] cardViewIds = {R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};
        for (int cardViewId : cardViewIds) {
            findViewById(cardViewId).setVisibility(View.INVISIBLE);
        }
    }

    public void setAllCardViewsVisible() {
        int[] cardViewIds = {R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};
        for (int cardViewId : cardViewIds) {
            findViewById(cardViewId).setVisibility(View.VISIBLE);
        }
    }
}