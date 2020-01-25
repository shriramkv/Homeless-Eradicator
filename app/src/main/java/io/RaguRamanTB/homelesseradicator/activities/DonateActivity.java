package io.RaguRamanTB.homelesseradicator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.BackgroundWorker;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class DonateActivity extends AppCompatActivity {

    private TextView donateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Toolbar toolbar = findViewById(R.id.donate_toolbar);
        toolbar.setTitle("Donate");
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        donateText = findViewById(R.id.donateText);

        String message = Utils.DONATIONS;
        donateText.setText(message);
    }
}
