package com.example.citycard;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RouteFragment extends Fragment {

private static RouteSchedule parentActivity;
    public static void setParentActivity(RouteSchedule activity) {
        parentActivity = activity;
    }
    private static final String ARG_ROUTE_INFO = "routeInfo";


    public static RouteFragment newInstance(String routeInfo) {
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROUTE_INFO, routeInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        TextView routeTextView = view.findViewById(R.id.routeTextView);
        routeTextView.setText(getArguments().getString(ARG_ROUTE_INFO));

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
