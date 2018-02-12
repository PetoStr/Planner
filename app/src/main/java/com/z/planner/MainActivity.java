package com.z.planner;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CalendarView;
import android.widget.EditText;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private CalendarView calendarView;

    private PlannerDatabase plannerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text_1);
        calendarView = findViewById(R.id.calendarView);

        editText.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                editText.getWindowVisibleDisplayFrame(rect);

                int keypadHeight = editText.getRootView().getHeight() - rect.bottom;

                if (keypadHeight > 0) { /* keyboard is opened */
                    calendarView.setVisibility(View.GONE);
                    editText.setTop(5);

                } else { /* keyboard is closed */
                    calendarView.setVisibility(View.VISIBLE);
                    storeCalendarData(calendarView.getDate(), editText.getText());
                }

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                long time = calendarView.getDate();
                String date = new SimpleDateFormat("dd/MM/yyyy").format(time);
                try {
                    editText.setText(plannerDatabase.getPlan(date));
                } catch (Exception e) {
                    editText.setText("");
                }
            }
        });

        plannerDatabase = new PlannerDatabase(getApplicationContext());
    }

    public void storeCalendarData(long time, Editable text) {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(time);

        plannerDatabase.storePlan(date, text.toString());

        String stored = plannerDatabase.getPlan(date);
    }

}
