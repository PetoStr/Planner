package com.z.planner;

/**
 * Created by zenit on 29. 1. 2018.
 */

public class PlannerDatabase {

    private static final String TABLE_NAME = "plans";
    private static final String COLUMN_DATE_NAME = "date";
    private static final String COLUMN_PLAN_NAME = "plan";

    private static final String SQL_DATABASE_NAME = "planner.db";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_DATE_NAME + " TEXT,"
            + COLUMN_PLAN_NAME + " TEXT"
            + ");";
}
