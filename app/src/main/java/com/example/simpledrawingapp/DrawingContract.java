package com.example.simpledrawingapp;

import android.provider.BaseColumns;

public final class DrawingContract {

    // Private constructor to prevent instantiation
    private DrawingContract() {}

    // Inner class that defines the drawing table contents
    public static class DrawingEntry implements BaseColumns {
        public static final String TABLE_NAME = "drawings";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_IMAGE = "image";
    }
}
