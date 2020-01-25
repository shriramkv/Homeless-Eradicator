package io.RaguRamanTB.homelesseradicator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.ForumHelper;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener {

    private static ListView listView;
    private static ArrayAdapter arrayAdapter;
    private Button postIdea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        Toolbar toolbar = findViewById(R.id.forum_toolbar);
        toolbar.setTitle("Forum");
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter(this, R.layout.list_view,Utils.arrayList);

        postIdea = findViewById(R.id.postIdea);

        postIdea.setOnClickListener(this);

        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.postIdea) {
            Toast.makeText(this, "Idea Posted successfully!",Toast.LENGTH_SHORT).show();
        }
    }
}
