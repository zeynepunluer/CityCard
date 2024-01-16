package com.example.citycard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;

public class RouteSchedule extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private CollectionReference schedulesCollection;
    public void onCustomFragmentClose() {
        getSupportFragmentManager().popBackStack();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_schedule);
        firestore = FirebaseFirestore.getInstance();
        schedulesCollection = firestore.collection("Schedules");

        fetchSchedulesAndRefreshUI();

        CardView toolBarCardView = findViewById(R.id.toolBar);
        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

    private void fetchSchedulesAndRefreshUI() {
        System.out.println(schedulesCollection);
        schedulesCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Schedules> scheduleList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    if (document.exists()) {
                        Schedules schedule = document.toObject(Schedules.class);
                        scheduleList.add(schedule);
                    } else {
                        Log.e("Firestore", "Document is null");
                    }
                }
                updateUIWithSchedules(scheduleList);
            } else {
                Log.e("Firestore", "Error fetching documents: " + task.getException());
            }
        });
    }
    private void updateUIWithSchedules(ArrayList<Schedules> scheduleList) {
        int[] cardViewIds = {R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};
        for (int i = 0; i < Math.min(scheduleList.size(), cardViewIds.length); i++) {
            int cardViewId = cardViewIds[i];
            CardView cardView = findViewById(cardViewId);
            final int position = i;cardView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (position < scheduleList.size()) {
                        openCustomFragment(scheduleList.get(position));
                        setAllCardViewsInvisible();
                    }
                }
            });
        }
    }

    private void setAllCardViewsInvisible() {
        int[] cardViewIds = {R.id.toolBar,R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};
        for (int cardViewId : cardViewIds) {
            findViewById(cardViewId).setVisibility(View.INVISIBLE);
        }
    }

    public void setAllCardViewsVisible() {
        int[] cardViewIds = {R.id.toolBar,R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};
        for (int cardViewId : cardViewIds) {
            findViewById(cardViewId).setVisibility(View.VISIBLE);
        }
    }
}