package com.z.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        EditText editText = (EditText) view;
        calendar.setVisibility(View.GONE);
    }
}
