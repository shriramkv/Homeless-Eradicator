package io.RaguRamanTB.homelesseradicator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.adapters.GridAdapter;
import io.RaguRamanTB.homelesseradicator.models.GridItem;

public class HomeFragment extends Fragment {

    View view;
    GridAdapter adapter;
    GridView gridView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = view.findViewById(R.id.landing_grid);
        adapter = new GridAdapter(getActivity(),getData());
        gridView.setAdapter(adapter);
        return view;
    }

    private ArrayList<GridItem> getData() {
        ArrayList<GridItem> menuItems = new ArrayList<>();
        GridItem m = new GridItem();
        m.setGridPicture(R.drawable.homeless);
        m.setGridText("Homeless Identified");
        menuItems.add(m);

        m = new GridItem();
        m.setGridPicture(R.drawable.donate);
        m.setGridText("Donate");
        menuItems.add(m);

        m = new GridItem();
        m.setGridPicture(R.drawable.forum);
        m.setGridText("Forum");
        menuItems.add(m);

        return menuItems;
    }
}
