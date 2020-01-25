package io.RaguRamanTB.homelesseradicator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.ForumHelper;
import io.RaguRamanTB.homelesseradicator.helpers.ForumPostHelper;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class ForumActivity extends AppCompatActivity implements View.OnClickListener {

    private static ListView listView;
    private EditText title, idea;
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
        title = findViewById(R.id.forumTitle);
        idea = findViewById(R.id.forumIdea);

        arrayAdapter = new ArrayAdapter(this, R.layout.list_view,Utils.arrayList);

        postIdea = findViewById(R.id.postIdea);
        postIdea.setOnClickListener(this);

        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.postIdea) {
            putIdea();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            ForumPostHelper forumPostHelper = new ForumPostHelper(this);
            forumPostHelper.execute("Refresh");
            arrayAdapter = new ArrayAdapter(this, R.layout.list_view, Utils.arrayList);
            listView.setAdapter(arrayAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    private void putIdea() {
        String getTitle = title.getText().toString();
        String getIdea = idea.getText().toString();

        if ( getTitle.equals("") || getTitle.length() == 0 || getIdea.equals("") || getIdea.length() == 0 ) {
            Toast.makeText(this,"Enter both title and idea!",Toast.LENGTH_SHORT).show();
        } else {
            ForumPostHelper forumPostHelper = new ForumPostHelper(this);
            forumPostHelper.execute("Post",getTitle, getIdea);
        }
    }
}
