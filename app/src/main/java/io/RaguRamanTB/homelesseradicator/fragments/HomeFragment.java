package io.RaguRamanTB.homelesseradicator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import io.RaguRamanTB.homelesseradicator.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button homeless, forum, donate;
    private View view;

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

        homeless.setOnClickListener(this);
        forum.setOnClickListener(this);
        donate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeless_identified:
                Toast.makeText(getContext(),"Go to homeless identified!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.userForum:
                Toast.makeText(getContext(),"Go to Forum!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.userDonate:
                Toast.makeText(getContext(),"Go to Donate!",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
