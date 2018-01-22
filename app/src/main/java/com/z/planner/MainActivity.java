package com.z.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText editText;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.text_1);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

        editText.setOnFocusChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        if (editText.isFocused()) {
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        }
        super.onBackPressed();
    }

    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            calendarView.setVisibility(View.GONE);
        } else {
            calendarView.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View view) {
        /*CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        EditText editText = (EditText) view;
        calendar.setVisibility(View.GONE);*/
    }
}
