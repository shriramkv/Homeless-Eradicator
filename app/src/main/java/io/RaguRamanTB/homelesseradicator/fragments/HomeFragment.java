package io.RaguRamanTB.homelesseradicator.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.activities.DonateActivity;
import io.RaguRamanTB.homelesseradicator.activities.ForumActivity;
import io.RaguRamanTB.homelesseradicator.activities.HomelessIdentifiedActivity;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button homeless, forum, donate;
    private View view;
    private TextView welcomeText;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        homeless = view.findViewById(R.id.homeless_identified);
        forum = view.findViewById(R.id.userForum);
        donate = view.findViewById(R.id.userDonate);
        welcomeText = view.findViewById(R.id.helloText);

        String message = "Hello, "+ Utils.USERNAME + "!";

        welcomeText.setText(message);

        homeless.setOnClickListener(this);
        forum.setOnClickListener(this);
        donate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeless_identified:
                Intent intent = new Intent(getContext(), HomelessIdentifiedActivity.class);
                startActivity(intent);
                break;

            case R.id.userForum:
                Intent intent2 = new Intent(getContext(), ForumActivity.class);
                startActivity(intent2);
                break;

            case R.id.userDonate:
                Intent intent1 = new Intent(getContext(), DonateActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
