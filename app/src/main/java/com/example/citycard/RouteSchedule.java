package com.example.citycard;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class RouteSchedule extends AppCompatActivity {
    public void onCustomFragmentClose() {
        getSupportFragmentManager().popBackStack();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_schedule);

        CardView toolBarCardView = findViewById(R.id.toolBar);

        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        int[] cardViewIds = {R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};

        for (int i = 0; i < cardViewIds.length; i++) {
            final int cardViewId = cardViewIds[i];
            findViewById(cardViewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCustomFragment("Rota Bilgisi " + (cardViewId - R.id.cardView2 + 1));
                    setAllCardViewsInvisible();
                }
            });
        }
    }

    private void openCustomFragment(String routeInfo) {
        List<String> scheduleList = createSampleScheduleList(); // Örnek bir liste oluştur

        RouteFragment customFragment = RouteFragment.newInstance(routeInfo, scheduleList);
        customFragment.setParentActivity(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, customFragment)
                .addToBackStack(null)
                .commit();
    }

    private List<String> createSampleScheduleList() {
        List<String> sampleList = new ArrayList<>();
        sampleList.add("09:00");
        sampleList.add("09:30");
        sampleList.add("10:00");
        sampleList.add("10:30");
        sampleList.add("11:00");
        sampleList.add("11:30");
        sampleList.add("12:30");
        sampleList.add("09:00");
        sampleList.add("09:30");
        sampleList.add("10:00");
        sampleList.add("10:30");
        sampleList.add("11:00");
        sampleList.add("11:30");
        sampleList.add("12:30");
        sampleList.add("09:00");
        sampleList.add("09:30");
        sampleList.add("10:00");
        sampleList.add("10:30");
        sampleList.add("11:00");
        sampleList.add("11:30");
        sampleList.add("12:30");
        return sampleList;
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