package com.example.citycard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.FirestoreClient;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            retrieveMapData(googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void retrieveMapData(GoogleMap googleMap) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Firestore'dan "harita_noktalari" koleksiyonundaki belgeleri sorgula
        db.collection("Locations")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Firestore'dan belge verilerini çek
                            double latitude = document.getDouble("latitude");
                            double longitude = document.getDouble("longitude");

                            // LatLng nesnesini oluştur ve haritada işaretle
                            LatLng location = new LatLng(latitude, longitude);
                            googleMap.addMarker(new MarkerOptions().position(location).title("Marker"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                        }
                    } else {
                        // Firestore'dan veri alınırken hata oluştuğunda logla
                        Log.w("Firestore", "Error getting documents.", task.getException());
                    }
                });
    }
}
