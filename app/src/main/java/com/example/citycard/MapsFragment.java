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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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

        // Firestore'dan "Locations" koleksiyonundaki belgeleri sorgula
        db.collection("Locations")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Kullanıcının konumu temsil eden bir marker ekle (örneğin, Istanbul şehri)
                        LatLng currentLocation = new LatLng(37.1621378, 28.3724131);
                        googleMap.addMarker(new MarkerOptions()
                                .position(currentLocation)
                                .title("Current Location")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))); // Mavi renkte bir marker

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Firestore'dan belge verilerini çek
                            double latitude = document.getDouble("latitude");
                            double longitude = document.getDouble("longitude");

                            // LatLng nesnesini oluştur ve haritada işaretle
                            LatLng location = new LatLng(latitude, longitude);
                            googleMap.addMarker(new MarkerOptions()
                                    .position(location)
                                    .title("Bus Stop")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
                        }


                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));

                    } else {
                        // Firestore'dan veri alınırken hata oluştuğunda logla
                        Log.w("Firestore", "Error getting documents.", task.getException());
                    }
                });
    }
}
