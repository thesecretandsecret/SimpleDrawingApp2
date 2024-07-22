package com.example.simpledrawingapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DrawingDbHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "drawings.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement to create the drawing table
    private static final String SQL_CREATE_DRAWING_TABLE =
            "CREATE TABLE " + DrawingContract.DrawingEntry.TABLE_NAME + " (" +
                    DrawingContract.DrawingEntry._ID + " INTEGER PRIMARY KEY," +
                    DrawingContract.DrawingEntry.COLUMN_NAME_TITLE + " TEXT," +
                    DrawingContract.DrawingEntry.COLUMN_NAME_IMAGE + " BLOB)";

    // SQL statement to drop the drawing table if it exists
    private static final String SQL_DELETE_DRAWING_TABLE =
            "DROP TABLE IF EXISTS " + DrawingContract.DrawingEntry.TABLE_NAME;

    public DrawingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the drawing table
        db.execSQL(SQL_CREATE_DRAWING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the drawing table if it exists
        db.execSQL(SQL_DELETE_DRAWING_TABLE);
        // Recreate the drawing table
        onCreate(db);
    }
}
