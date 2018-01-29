package com.z.planner;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.text_1);
        calendarView = (CalendarView) findViewById(R.id.calendarView);

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
    }

    public void storeCalendarData(long time, Editable text) {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(time);

        Toast.makeText(this, "date: " + date, Toast.LENGTH_LONG).show();
    }

    public void onClick(View view) {

    }
}
