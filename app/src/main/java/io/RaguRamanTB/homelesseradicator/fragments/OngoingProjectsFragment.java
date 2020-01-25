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

public class OngoingProjectsFragment extends Fragment {

    private View view;
    private TextView ongoingText;
    private static ListView listView;
    private static ArrayAdapter arrayAdapter;

    public OngoingProjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ongoing_projects, container, false);
        ongoingText = view.findViewById(R.id.ongoingText);
        listView = view.findViewById(R.id.listView4);
        String message = "Among the amazing ideas given by our community members, based on the available funding we try to execute some of these ideas. Here are such ongoing projects based on the given ideas";
        ongoingText.setText(message);
        arrayAdapter = new ArrayAdapter(getContext(),R.layout.list_view,Utils.arrayList4);
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
