package io.RaguRamanTB.homelesseradicator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.RaguRamanTB.homelesseradicator.fragments.BestIdeasFragment;
import io.RaguRamanTB.homelesseradicator.fragments.HappyFacesFragment;
import io.RaguRamanTB.homelesseradicator.fragments.HomeFragment;
import io.RaguRamanTB.homelesseradicator.fragments.OngoingProjectsFragment;
import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.fragments.TopDonorsFragment;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;

public class FunctionsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        Toolbar toolbar = findViewById(R.id.DashToolbar);
        toolbar.setTitle("Dashboard");
        toolbar.setNavigationIcon(R.drawable.heart_30);
        setSupportActionBar(toolbar);

        loadFragment(new HomeFragment());

        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Utils.USERNAME="";
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to SIGNOUT", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(this, MyProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

/*    private void logout() {
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
*/

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

            case R.id.home:
                fragment = new HomeFragment();
                break;

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
        }

        return loadFragment(fragment);
    }
}
