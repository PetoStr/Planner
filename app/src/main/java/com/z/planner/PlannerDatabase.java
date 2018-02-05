package com.z.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zenit on 29. 1. 2018.
 */

public class PlannerDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "planner.db";

    private static final String TABLE_NAME = "plans";
    private static final String COLUMN_DATE_NAME = "date";
    private static final String COLUMN_PLAN_NAME = "plan";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                                                + COLUMN_DATE_NAME + " TEXT,"
                                                + COLUMN_PLAN_NAME + " TEXT"
                                                + ");";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Context context;

    public PlannerDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    public long storePlan(String date, String plan) {
        SQLiteDatabase database = this.getWritableDatabase();

        long rowId;


        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATE_NAME, date);
        contentValues.put(COLUMN_PLAN_NAME, plan);

        boolean exists = getPlan(date).split(":")[0].equals("1");

        boolean isEmpty = plan.trim().equals("");

        if (isEmpty) {
            database.delete(TABLE_NAME, COLUMN_DATE_NAME + " = '" + date + "'", null);
            return -1;
        }

        if (exists) {
            rowId = database.update(TABLE_NAME, contentValues, COLUMN_DATE_NAME + " = '" + date + "'", null);
        } else {
            rowId = database.insert(TABLE_NAME, null, contentValues);
        }

        database.close();
        return rowId;
    }

    public String getPlan(String date) {
       SQLiteDatabase database = this.getReadableDatabase();

       String selectQuery = "SELECT " + COLUMN_PLAN_NAME + " FROM " + TABLE_NAME
                            + " WHERE " + COLUMN_DATE_NAME + " = '"   + date + "'";

        Cursor c = database.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            return "1:" + c.getString(0);
        } else {
            Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
            return "0:";
        }
    }

}
