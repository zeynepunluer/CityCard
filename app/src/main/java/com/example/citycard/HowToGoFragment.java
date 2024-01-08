package com.example.citycard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HowToGoFragment extends Fragment {
    private static HowToGo parentActivity; // HowToGo sınıfından bir referans tanımlayın

    public static void setParentActivity(HowToGo activity) {
        parentActivity = activity;
    }


    private static final String ARG_HOWTOGO_INFO = "routeInfo";

    public static HowToGoFragment newInstance(String routeInfo) {
        HowToGoFragment fragment = new HowToGoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HOWTOGO_INFO, routeInfo);
        fragment.setArguments(args);
        return fragment;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_to_go, container, false);

        TextView howTextView = view.findViewById(R.id.howToGoTextView);
        howTextView.setText(getArguments().getString(ARG_HOWTOGO_INFO));

        CardView closeButton = view.findViewById(R.id.toolBar);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentActivity != null) {
                    parentActivity.setAllCardViewsVisible();
                    parentActivity.onCustomFragmentClose();
                }
            }
        });

        return view;
    }
}
