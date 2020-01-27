package io.RaguRamanTB.homelesseradicator.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.BackgroundWorker;
import io.RaguRamanTB.homelesseradicator.helpers.EditProfileHelper;
import io.RaguRamanTB.homelesseradicator.helpers.Utils;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private EditText dob;
    private DatePickerDialog.OnDateSetListener mdate;
    private EditText name, address, email, phone_number, profession, password, conf_password, org_name, org_address, org_city;
    private CheckBox is_volunteer, is_org_incharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setTitle("Edit Profile");
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String apikey = "AIzaSyA-jTNigOJ8f3zQ6qketJ1QRVLTy7rkduo";

        name = findViewById(R.id.name_e);
        address = findViewById(R.id.address_e);
        email = findViewById(R.id.emailID_e);
        phone_number = findViewById(R.id.phoneNumber_e);
        dob = findViewById(R.id.dob_e);
        is_org_incharge = findViewById(R.id.orgCheckbox_e);
        is_volunteer = findViewById(R.id.volCheckbox_e);
        profession = findViewById(R.id.profession_e);
        password = findViewById(R.id.reg_password_e);
        conf_password = findViewById(R.id.reg_conf_password_e);
        org_name = findViewById(R.id.org_name_e);
        org_address = findViewById(R.id.org_address_e);
        org_city = findViewById(R.id.org_city_e);

        register = findViewById(R.id.register_e);

        name.setText(Utils.profile.get(0));
        address.setText(Utils.profile.get(1));
        email.setText(Utils.profile.get(2));
        phone_number.setText(Utils.profile.get(3));
        profession.setText(Utils.profile.get(4));
        dob.setText(Utils.profile.get(5));
        org_name.setText(Utils.profile.get(6));
        org_address.setText(Utils.profile.get(7));
        org_city.setText(Utils.profile.get(8));

        final TextFieldBoxes textFieldBoxes = findViewById(R.id.text_field_boxes206);
        textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dobSelector();
                }
            }
        });

        mdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dob.setText(date);
            }
        };

        if(!Places.isInitialized()) {
            Places.initialize(this,apikey);
        }

        final TextFieldBoxes textFieldBoxes1 = findViewById(R.id.text_field_boxes211);
        textFieldBoxes1.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLoc();
            }
        });

        register.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void dobSelector() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth,
                mdate,
                year, month, day);
        dialog.show();
    }

    private void selectLoc() {
        int AUTOCOMPLETE_REQUEST_CODE = 1;
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .setTypeFilter(TypeFilter.CITIES)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                org_city.setText(place.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("Location Error", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goToRegistration() {
        String getName = name.getText().toString();
        String getAddress = address.getText().toString();
        String getEmailId = email.getText().toString();
        String getPhoneNumber = phone_number.getText().toString();
        String getProfession = profession.getText().toString();
        String getDob = dob.getText().toString();
        String getPassword = password.getText().toString();
        String getConfPassword = conf_password.getText().toString();
        String getOrgName = org_name.getText().toString();
        String getOrgAddress = org_address.getText().toString();
        String getOrgCity = org_city.getText().toString();
        String is_a_volunteer = "FALSE", is_a_org_incharge = "FALSE";

        if (is_volunteer.isChecked()) {
            is_a_volunteer = "TRUE";
        }

        if (is_org_incharge.isChecked()) {
            is_a_org_incharge = "TRUE";
        }

        String type = "EditProfile";

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        if (getName.equals("") || getName.length() == 0
                || getAddress.equals("") || getAddress.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getPhoneNumber.equals("") || getPhoneNumber.length() == 0
                || getProfession.equals("") || getProfession.length() == 0
                || getDob.equals("") || getDob.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        } else if (!m.find()) {
            Toast.makeText(this, "Enter a valid Email ID!", Toast.LENGTH_SHORT).show();
        } else if (!getConfPassword.equals(getPassword)) {
            Toast.makeText(this, "Both passwords doesn't match!", Toast.LENGTH_SHORT).show();
        } else if (is_org_incharge.isChecked() && (getOrgName.equals("") || getOrgName.length() == 0 || getOrgAddress.equals("") || getOrgAddress.length() == 0 || getOrgCity.equals("") || getOrgCity.length() == 0)) {
            Toast.makeText(this, "Please enter the Organisation details if an Organisation In-charge!", Toast.LENGTH_SHORT).show();
        } else {
            EditProfileHelper editProfileHelper = new EditProfileHelper(this);
            editProfileHelper.execute(type, getName, getAddress, getEmailId, getPhoneNumber, getProfession, getDob, getPassword, getOrgName, getOrgAddress, getOrgCity, is_a_volunteer, is_a_org_incharge);
//            Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_e) {
            goToRegistration();
        }
    }
}
