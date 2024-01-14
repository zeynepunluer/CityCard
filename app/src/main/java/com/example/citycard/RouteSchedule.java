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
        ArrayList<Schedules> scheduleList = new ArrayList<>();
        // Firebase Firestore ve schedules koleksiyonunu başlatın
        firestore = FirebaseFirestore.getInstance();
        schedulesCollection = firestore.collection("Schedules");

        // Firestore'dan veriyi çekip UI'yi güncelleyin
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
        // Firestore'dan veriyi çekmek için schedules koleksiyonunu kullanın
        System.out.println(schedulesCollection);
        schedulesCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Firestore'dan gelen belgeleri alın
                ArrayList<Schedules> scheduleList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    if (document.exists()) {
                        Schedules schedule = document.toObject(Schedules.class);
                        scheduleList.add(schedule);
                    } else {
                        Log.e("Firestore", "Document is null");
                    }
                }

                // Log the size of scheduleList
                Log.d("Firestore", "Number of schedules: " + scheduleList.size());

                // UI'yi güncelleyin
                updateUIWithSchedules(scheduleList);
            } else {
                // Veri çekme başarısız olduysa burada işlemleri gerçekleştirebilirsiniz
                Log.e("Firestore", "Error fetching documents: " + task.getException());
            }
        });
    }

    private void updateUIWithSchedules(ArrayList<Schedules> scheduleList) {
        // CardView'ların ID'lerini bir diziye ekleyin
        int[] cardViewIds = {R.id.cardView2, R.id.cardView3, R.id.cardView4, R.id.cardView5, R.id.cardView6, R.id.cardView7};

        // Her bir CardView için tıklama olayını ayarlamak için döngü kullanın
        for (int i = 0; i < Math.min(scheduleList.size(), cardViewIds.length); i++) {
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
                    // Tıklanan CardView'ın konumu, scheduleList'in sınırları içinde mi kontrol et
                    if (position < scheduleList.size()) {
                        // Eğer içindeyse, ilgili schedule'ı al ve işlemleri gerçekleştir
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