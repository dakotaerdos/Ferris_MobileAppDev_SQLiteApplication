package com.zybooks.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

public class ScheduleDatabase extends SQLiteOpenHelper {

    private static ScheduleDatabase scheduleDatabase;

    private static final String DATABASE_NAME = "Schedule.db";
    private static final int VERSION = 1;

     public static ScheduleDatabase getInstance(Context context) {
        if (scheduleDatabase == null) {
            scheduleDatabase = new ScheduleDatabase(context);
        }
        return scheduleDatabase;
    }

    public ScheduleDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class ScheduleTable {
        private static final String TABLE = "schedules";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "title";
        private static final String COL_DESCRIPTION = "description";
        private static final String COL_LOCATION = "location";
        private static final String COL_INSTRUCTOR = "instructor";
        private static final String COL_DATE = "date";
        private static final String COL_TIME = "time";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ScheduleTable.TABLE + " (" +
                ScheduleTable.COL_ID + " integer primary key autoincrement, " +
                ScheduleTable.COL_NAME + " text, " +
                ScheduleTable.COL_DESCRIPTION + " text, " +
                ScheduleTable.COL_LOCATION + " text, " +
                ScheduleTable.COL_INSTRUCTOR + " text, " +
                ScheduleTable.COL_DATE + " text, " +
                ScheduleTable.COL_TIME + " text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("drop table if exists " + ScheduleTable.TABLE);
        onCreate(db);
    }

    public long addClass(String name, String description, String location, String instructor,
                         String date, String time) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScheduleTable.COL_NAME, name);
        values.put(ScheduleTable.COL_DESCRIPTION, description);
        values.put(ScheduleTable.COL_LOCATION, location);
        values.put(ScheduleTable.COL_INSTRUCTOR, instructor);
        values.put(ScheduleTable.COL_DATE, date);
        values.put(ScheduleTable.COL_TIME, time);

        long ScheduleId = db.insert(ScheduleTable.TABLE, null, values);
        return ScheduleId;
    }

    public boolean updateClass(long id, String name, String description, String location, String instructor,
                               String date, String time) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScheduleTable.COL_NAME, name);
        values.put(ScheduleTable.COL_DESCRIPTION, description);
        values.put(ScheduleTable.COL_LOCATION, location);
        values.put(ScheduleTable.COL_INSTRUCTOR, instructor);
        values.put(ScheduleTable.COL_DATE, date);
        values.put(ScheduleTable.COL_TIME, time);

        int rowsUpdated = db.update(ScheduleTable.TABLE, values, "_id = ?",
                new String[] { Float.toString(id) });
        return rowsUpdated > 0;
    }

    public boolean deleteClass(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(ScheduleTable.TABLE, ScheduleTable.COL_ID + " = ?",
                new String[] { Long.toString(id) });
        return rowsDeleted > 0;
    }

    public Class selectClass(String id) {
        SQLiteDatabase db = getReadableDatabase();

        Class _class;

        String sql = "SELECT * FROM " + ScheduleTable.TABLE + " WHERE _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { id });
        if (cursor.moveToFirst()) {

             _class = new Class(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
            return _class;
        }
        return null;
    }

    public ArrayList<Class> selectAllClasses() {

        ArrayList<Class> classes = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + ScheduleTable.TABLE;
        Cursor cursor = db.rawQuery(sql, null);


        if (cursor.moveToFirst()) {
            do {
                classes.add(new Class(cursor.getLong(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5),
                        cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return classes;
    }
}

