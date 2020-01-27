package io.RaguRamanTB.homelesseradicator.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.LocationHelpers;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class HomelessIdentifiedActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1855;
    private ImageView imageView;
    private EditText location;
    private Button attach, pickup, drop;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeless_identified);
        Toolbar toolbar = findViewById(R.id.homeless_toolbar);
        toolbar.setTitle("Homeless Identified");
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String apikey = "AIzaSyA-jTNigOJ8f3zQ6qketJ1QRVLTy7rkduo";

        imageView = findViewById(R.id.attachedImage);
        attach = findViewById(R.id.attachImage);
        pickup = findViewById(R.id.pickup);
        drop = findViewById(R.id.drop);
        location = findViewById(R.id.city);

        if(!Places.isInitialized()) {
            Places.initialize(this,apikey);
        }

        final TextFieldBoxes textFieldBoxes1 = findViewById(R.id.text_field_boxes_101);
        textFieldBoxes1.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLocCity();
            }
        });

        attach.setOnClickListener(this);
        pickup.setOnClickListener(this);
        drop.setOnClickListener(this);
    }

    public void selectLocCity() {
        int AUTOCOMPLETE_REQUEST_CODE = 1;
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .setTypeFilter(TypeFilter.CITIES)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Permission Granted!",Toast.LENGTH_SHORT).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, RESULT_LOAD_IMAGE);
            } else {
                Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == RESULT_LOAD_IMAGE) && (resultCode == Activity.RESULT_OK)) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                location.setText(place.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("Location Error", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attachImage:
                attachImage();
                break;

            case R.id.pickup:
                showAlertDialog();
                break;

            case R.id.drop:
                goToDrop();
                break;
        }
    }

    private void goToDrop() {
        String city = location.getText().toString();

        if (hasImage(imageView) && !(city.equals("") || city.length() == 0)) {
            LocationHelpers locationHelpers = new LocationHelpers(this);
            locationHelpers.execute("Nearby",city);
        } else {
            Toast.makeText(this,"Please click a picture of homeless for verification and enter your location",Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialog() {
        alertDialog = new AlertDialog.Builder(this).create();

        String city = location.getText().toString();

        if (hasImage(imageView) && !(city.equals("") || city.length() == 0)) {
            String message = "A volunteer has been pinged to pickup the homeless. You can leave or wait until our volunteer arrives";
            alertDialog.setTitle("Intimation Successful!");
            alertDialog.setMessage(message);
            alertDialog.show();
            alertDialog.setCancelable(true);
        } else {
            Toast.makeText(this,"Please click a picture of homeless for verification and enter your location",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }

        return hasImage;
    }

    private void attachImage() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        } else {
            Intent intent = new Intent (android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        }

    }
}
