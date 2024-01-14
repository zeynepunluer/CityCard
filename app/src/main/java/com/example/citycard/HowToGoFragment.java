package com.example.citycard;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
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

        TextView howTitleTextView = view.findViewById(R.id.howToGoTextView);
        TextView howDescriptionTextView = view.findViewById(R.id.howToGoDescriptionTextView);

        HTGDirections directions = (HTGDirections) getArguments().getSerializable(ARG_HOWTOGO_INFO);

        if (directions != null) {
            howTitleTextView.setText(directions.getTitle());
            howDescriptionTextView.setText(formatBulletList(directions.getDescription()));
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

    private SpannableString formatBulletList(String description) {
        String[] lines = description.split("\n");
        SpannableString spannableString = new SpannableString("");
        for (String line : lines) {
            String formattedLine = "• " + line + "\n";
            spannableString = new SpannableString(spannableString + formattedLine);
            spannableString.setSpan(new BulletSpan(8), spannableString.length() - formattedLine.length(), spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }
}