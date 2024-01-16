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


    private static final String ARG_HOWTOGO_INFO = "directionInfo";

    public static HowToGoFragment newInstance(HTGDirections directions) {
        HowToGoFragment fragment = new HowToGoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOWTOGO_INFO, directions);
        fragment.setArguments(args);
        return fragment;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_to_go, container, false);

        TextView location = view.findViewById(R.id.location);
        TextView description1 = view.findViewById(R.id.TxtDescription1);
        TextView description2 = view.findViewById(R.id.TxtDescription2);
        TextView description3 = view.findViewById(R.id.TxtDescription3);
        TextView description4 = view.findViewById(R.id.TxtDescription4);
        TextView description5 = view.findViewById(R.id.TxtDescription5);



        HTGDirections directions = (HTGDirections) getArguments().getSerializable(ARG_HOWTOGO_INFO);

        if (directions != null) {
            location.setText(directions.getTitle());
            description1.setText(directions.getDescription1());
            description2.setText(directions.getDescription2());
            description3.setText(directions.getDescription3());
            description4.setText(directions.getDescription4());
            description5.setText(directions.getDescription5());



        }

        CardView closeButton = view.findViewById(R.id.toolBarFrag);
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