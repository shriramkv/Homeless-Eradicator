package io.RaguRamanTB.homelesseradicator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class BestIdeasFragment extends Fragment {

    private View view;
    private TextView ideasText;
    private static ListView listView;
    private static ArrayAdapter arrayAdapter;

    public BestIdeasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_best_ideas, container, false);
        ideasText = view.findViewById(R.id.bestIdeaText);
        listView = view.findViewById(R.id.listView3);
        String message = "We have a team to rate the ideas posted by our community members "+ Utils.USERNAME +
                ". Here we present you the top rated ideas which are possible and feasible to execute. We welcome every ideas and encourage you too to suggest some amazing ideas to eradicate homelessness, "+Utils.USERNAME+" :)";
        ideasText.setText(message);
        arrayAdapter = new ArrayAdapter(getContext(),R.layout.list_view,Utils.arrayList3);
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
