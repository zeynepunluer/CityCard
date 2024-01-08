package com.example.citycard;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RouteFragment extends Fragment {
    private static final String ARG_ROUTE_INFO = "routeInfo";
    private static final String ARG_SCHEDULE_LIST = "scheduleList";

    private static RouteSchedule parentActivity;

    public static void setParentActivity(RouteSchedule activity) {
        parentActivity = activity;
    }

    private static final String ARG_SCHEDULE = "schedule";
    private List<String> schedule;


    public static RouteFragment newInstance(String routeInfo, List<String> schedule) {
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROUTE_INFO, routeInfo);
        args.putStringArrayList(ARG_SCHEDULE_LIST, new ArrayList<>(schedule));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        TextView routeTextView = view.findViewById(R.id.routeTextView);
        routeTextView.setText(getArguments().getString(ARG_ROUTE_INFO));

        List<String> scheduleList = getArguments().getStringArrayList(ARG_SCHEDULE_LIST);

        // scheduleList'i kullanarak istediğiniz işlemleri yapabilirsiniz.

        // ListView ve ArrayAdapter'ı oluşturun
        ListView listView = view.findViewById(R.id.listView1);
        ListView listView2 = view.findViewById(R.id.listView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, scheduleList);

        // ListView'e Adapter'ı set edin
        listView.setAdapter(adapter);
        listView2.setAdapter(adapter);

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