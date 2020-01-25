package io.RaguRamanTB.homelesseradicator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class HappyFacesFragment extends Fragment {

    private View view;
    private TextView happyText;
    public HappyFacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_happy_faces, container, false);
        happyText = view.findViewById(R.id.happyFacesText);
        String message = "A smile in their face indicates the love towards you "+ Utils.USERNAME+
                " ! Millions and millions of people are waiting eagerly to get such a happy and smiling face. We wish and hope that you could contribute to their smiles till the end :)";
        happyText.setText(message);
        return view;
    }

}
