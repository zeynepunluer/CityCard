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
        // Firebase Firestore ve schedules koleksiyonunu başlatın
        firestore = FirebaseFirestore.getInstance();
        directionsCollection = firestore.collection("HTGDirections");
        // Firestore'dan veriyi çekip UI'yi güncelleyin
        fetchSchedulesAndRefreshUI();

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

//        CardView cardView3 = findViewById(R.id.cardView3);
//        CardView cardView4 = findViewById(R.id.cardView4);
//        CardView cardView5 = findViewById(R.id.cardView5);
//
//        cardView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCustomFragment(directionsList.get(0));
//                setAllCardViewsInvisible();
//            }
//        });
//
//        cardView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCustomFragment(directionsList.get(1));
//                setAllCardViewsInvisible();
//            }
//        });
//
//        cardView5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCustomFragment(directionsList.get(2));
//                setAllCardViewsInvisible();
//            }
//        });
    }

    private void fetchSchedulesAndRefreshUI() {
        directionsCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Firestore'dan gelen belgeleri alın
                ArrayList<HTGDirections> directionList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    if (document.exists()) {
                        HTGDirections directions = document.toObject(HTGDirections.class);
                        directionList.add(directions);
                    } else {
                        Log.e("Firestore", "Document is null");
                    }
                }

                // Log the size of scheduleList
                Log.d("Firestore", "Number of schedules: " + directionList.size());

                // UI'yi güncelleyin
                updateUIWithSchedules(directionList);
            } else {
                // Veri çekme başarısız olduysa burada işlemleri gerçekleştirebilirsiniz
                Log.e("Firestore", "Error fetching documents: " + task.getException());
            }
        });
    }

    private void updateUIWithSchedules(ArrayList<HTGDirections> directionList) {
        // CardView'ların ID'lerini bir diziye ekleyin
        int[] cardViewIds = {R.id.cardView3, R.id.cardView4, R.id.cardView5};

        // Her bir CardView için tıklama olayını ayarlamak için döngü kullanın
        for (int i = 0; i < Math.min(directionList.size(), cardViewIds.length); i++) {
            // CardView'ın ID'sini alın
            int cardViewId = cardViewIds[i];

            // ID'ye sahip CardView'ı bulun
            CardView cardView = findViewById(cardViewId);

            // Her bir tıklama olayında kullanmak üzere final bir değişken oluşturun
            final int position = i;

            // CardView'a tıklama olayını ekle
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tıklanan CardView'ın konumu, directionList'in sınırları içinde mi kontrol et
                    if (position < directionList.size()) {
                        // Eğer içindeyse, ilgili schedule'ı al ve işlemleri gerçekleştir
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