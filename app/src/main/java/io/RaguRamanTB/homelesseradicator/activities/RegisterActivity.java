package io.RaguRamanTB.homelesseradicator.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.RaguRamanTB.homelesseradicator.R;
import io.RaguRamanTB.homelesseradicator.helpers.BackgroundWorker;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    private Button register;
    private EditText dob;
    private DatePickerDialog.OnDateSetListener mdate;
    private EditText name, address, email, phone_number, profession, password, conf_password, org_name, org_address;
    private CheckBox is_volunteer, is_org_incharge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        dob = findViewById(R.id.dob);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.emailID);
        phone_number = findViewById(R.id.phoneNumber);
        is_org_incharge = findViewById(R.id.orgCheckbox);
        is_volunteer = findViewById(R.id.volCheckbox);
        profession = findViewById(R.id.profession);
        password = findViewById(R.id.reg_password);
        conf_password = findViewById(R.id.reg_conf_password);
        org_name = findViewById(R.id.org_name);
        org_address = findViewById(R.id.org_address);

        final TextFieldBoxes textFieldBoxes = findViewById(R.id.text_field_boxes8);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register) {
            goToRegistration();
        }
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

        String donation = "0";
        String is_a_volunteer = "FALSE", is_a_org_incharge = "FALSE";

        if (is_volunteer.isChecked()) {
            is_a_volunteer = "TRUE";
        }

        if (is_org_incharge.isChecked()) {
            is_a_org_incharge = "TRUE";
        }

        String type = "Register";

        Pattern p = Pattern.compile(regEx);
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
        } else if (is_org_incharge.isChecked() && (getOrgName.equals("") || getOrgName.length() == 0 || getOrgAddress.equals("") || getOrgAddress.length() == 0)) {
            Toast.makeText(this, "Please enter the Organisation details if an Organisation In-charge!", Toast.LENGTH_SHORT).show();
        } else {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, getName, getAddress, getEmailId, getPhoneNumber, getProfession, getDob, getPassword, donation, getOrgName, getOrgAddress, is_a_volunteer, is_a_org_incharge);
        }
    }
}
