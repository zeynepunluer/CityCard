package com.example.citycard;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class RouteFragment extends Fragment {   //this is a fragment to display time schedules for busses based on their timing on departure and destination locations
    private static RouteSchedule parentActivity;

    public static void setParentActivity(RouteSchedule activity) {
        parentActivity = activity;
    }

    private static final String ARG_SCHEDULE = "schedule";   // key for passing Schedules data as an argument to the fragment


    public static RouteFragment newInstance(Schedules schedule) {   // Creating a new instance of the fragment with specified Schedules data
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHEDULE,schedule);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        Schedules schedule = (Schedules) getArguments().getSerializable(ARG_SCHEDULE);
        List<String> scheduleList = schedule.getSchedule();




        ListView listView = view.findViewById(R.id.listView1);
        ListView listView2 = view.findViewById(R.id.listView2);
        TextView textView1 = view.findViewById(R.id.editTextDeparture);
        TextView textView2 = view.findViewById(R.id.editTextDestination);
        TextView backToBus = view.findViewById(R.id.backToBus);
        ArrayAdapter<String> adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, scheduleList) {
            @NonNull
            @Override                    // Creating an ArrayAdapter to populate the list views with schedule data
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // customizing the appearance of the text
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                return view;
            }
        };




        listView.setAdapter(adapter);
        listView2.setAdapter(adapter);
        textView1.setText(schedule.getDeparture());
        textView2.setText(schedule.getDestination());
        backToBus.setText(schedule.getBusName());



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