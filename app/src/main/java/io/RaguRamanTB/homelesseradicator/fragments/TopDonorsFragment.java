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

public class TopDonorsFragment extends Fragment {

    private View view;
    private TextView donorText;
    private static ListView listView;
    private static ArrayAdapter arrayAdapter;

    public TopDonorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_donors, container, false);
        donorText = view.findViewById(R.id.topDonorText);
        listView = view.findViewById(R.id.listView2);
        String message = "Here we present you the top donors of our community. We encourage you to donate more to top the list "+ Utils.USERNAME +" :) ";
        donorText.setText(message);
        arrayAdapter = new ArrayAdapter(getContext(),R.layout.list_view,Utils.arrayList2);
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
