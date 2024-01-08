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
import java.util.Arrays;
import java.util.List;

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
        // UI'yi Firestore'dan gelen veriyle güncelleyin
        // Örneğin, cardView3, cardView4 ve cardView5'e tıklanınca RouteFragment açılacak şekilde güncelleme yapabilirsiniz
        // Örnek olarak sadece cardView3'ü güncelliyorum, geri kalanları sizin iş mantığınıza göre ekleyebilirsiniz

        CardView cardView3 = findViewById(R.id.cardView3);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scheduleList.size() > 0) {
                    openCustomFragment(scheduleList.get(0));
                    setAllCardViewsInvisible();
                }
            }
        });

        // Diğer cardView'ları güncelleyin...
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