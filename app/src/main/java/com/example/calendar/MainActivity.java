package com.example.calendar;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView timeFieldInput;
    Button btnChangeDate, btnChangeTime;
    Calendar calendar = Calendar.getInstance();

    private final DatePickerDialog.OnDateSetListener onDateSetListener =
            (view, year, month, dayOfMonth) -> {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDateAndTime();
            };

    private final TimePickerDialog.OnTimeSetListener onTimeSetListener =
            (view, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                setDateAndTime();
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeFieldInput = findViewById(R.id.time_input);
        btnChangeDate = findViewById(R.id.button_change_date);
        btnChangeTime = findViewById(R.id.button_change_time);

        setDateAndTime();

        btnChangeDate.setOnClickListener(view -> new DatePickerDialog(MainActivity.this,
                onDateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show());

        btnChangeTime.setOnClickListener(view -> new TimePickerDialog(MainActivity.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true)
                .show());
    }

    private void setDateAndTime() {
        timeFieldInput.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_SHOW_TIME));
    }
}