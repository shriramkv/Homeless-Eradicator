package io.RaguRamanTB.homelesseradicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FunctionsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        Toolbar toolbar = findViewById(R.id.DashToolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setNavigationIcon(R.drawable.heart_30);
        setSupportActionBar(toolbar);

        loadFragment(new TopDonorsFragment());

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(FunctionsActivity.this);
        builder.setMessage("Do you really want to Logout?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog = builder.create();
        alertDialog.setTitle("Logout Confirmation");
        alertDialog.show();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.topDonors:
                fragment = new TopDonorsFragment();
                break;

            case R.id.bestIdea:
                fragment = new BestIdeasFragment();
                break;

            case R.id.ongoingProjects:
                fragment = new OngoingProjectsFragment();
                break;

            case R.id.happyFaces:
                fragment = new HappyFacesFragment();
                break;

            case R.id.myProfile:
                fragment = new MyProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
