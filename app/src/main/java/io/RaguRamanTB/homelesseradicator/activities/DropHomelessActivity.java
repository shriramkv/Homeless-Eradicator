package io.RaguRamanTB.homelesseradicator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class DropHomelessActivity extends AppCompatActivity {

    private static ListView listView;
    private static ArrayAdapter arrayAdapter;
    private TextView dropLocationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_homeless);
        Toolbar toolbar = findViewById(R.id.drop_homeless_toolbar);
        toolbar.setTitle("Drop Homeless");
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.locationList);
        dropLocationText = findViewById(R.id.dropLocationText);

        String message = "You can drop the homeless in any of the above location which is more nearer to you";
        dropLocationText.setText(message);

        arrayAdapter = new ArrayAdapter(this,R.layout.location_list, Utils.arrayList5);
        listView.setAdapter(arrayAdapter);
    }
}
