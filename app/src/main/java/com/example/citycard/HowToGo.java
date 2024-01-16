package com.example.citycard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HowToGo extends AppCompatActivity{
    private FirebaseFirestore firestore;
    private CollectionReference directionsCollection;
    public void onCustomFragmentClose() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_go);
        firestore = FirebaseFirestore.getInstance();
        directionsCollection = firestore.collection("HTGDirections");
        CardView toolBarCardView = findViewById(R.id.toolBar);
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


        toolBarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        fetchSchedulesAndRefreshUI();
    }

    private void fetchSchedulesAndRefreshUI() {
        directionsCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<HTGDirections> directionList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    if (document.exists()) {
                        HTGDirections directions = document.toObject(HTGDirections.class);
                        directionList.add(directions);
                    } else {
                        Log.e("Firestore", "Document is null");
                    }
                }
                updateUIWithSchedules(directionList);
            } else {
                Log.e("Firestore", "Error fetching documents: " + task.getException());
            }
        });
    }

    private void updateUIWithSchedules(ArrayList<HTGDirections> directionList) {
        int[] cardViewIds = {R.id.cardView3, R.id.cardView4, R.id.cardView5};
        for (int i = 0; i < Math.min(directionList.size(), cardViewIds.length); i++) {
            int cardViewId = cardViewIds[i];
            CardView cardView = findViewById(cardViewId);
            final int position = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position < directionList.size()) {
                        openCustomFragment(directionList.get(position));
                        setAllCardViewsInvisible();
                    }
                }
            });
        }
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
        int[] cardViewIds = {R.id.toolBar,R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5};
        for (int cardViewId : cardViewIds) {
            findViewById(cardViewId).setVisibility(View.INVISIBLE);
        }
    }

    public void setAllCardViewsVisible() {
        int[] cardViewIds = {R.id.toolBar,R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5};
        for (int cardViewId : cardViewIds) {
            findViewById(cardViewId).setVisibility(View.VISIBLE);
        }
    }
}