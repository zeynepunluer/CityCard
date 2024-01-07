package com.example.citycard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class CustomFragment extends Fragment {

    private CustomFragmentListener fragmentListener;
    private static final String ARG_ROUTE_INFO = "routeInfo";

    public static CustomFragment newInstance(String routeInfo) {
        CustomFragment fragment = new CustomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ROUTE_INFO, routeInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CustomFragmentListener) {
            fragmentListener = (CustomFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CustomFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);

        TextView routeTextView = view.findViewById(R.id.routeTextView);
        routeTextView.setText(getArguments().getString(ARG_ROUTE_INFO));

        CardView closeButton = view.findViewById(R.id.toolBar);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.setAllCardViewsVisible();
                fragmentListener.onCustomFragmentClose();
            }
        });

        return view;
    }
}
