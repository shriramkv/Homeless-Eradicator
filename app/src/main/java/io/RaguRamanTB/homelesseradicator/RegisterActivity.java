package io.RaguRamanTB.homelesseradicator;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private static Button register;
    private EditText dob;
    private DatePickerDialog.OnDateSetListener mdate;

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
                String date = dayOfMonth+"/"+month+"/"+year;
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
                year,month,day);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Toast.makeText(this,"Regration process to be done!",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
