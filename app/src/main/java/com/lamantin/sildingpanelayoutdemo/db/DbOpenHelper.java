package com.lamantin.sildingpanelayoutdemo.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lamantin.sildingpanelayoutdemo.models.api.Album;
import com.lamantin.sildingpanelayoutdemo.models.api.Photo;

public final class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String CREATE_ALBUMS = ""
            + "CREATE TABLE " + Album.TABLE + "("
            + Album._ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Album.ID + " INTEGER NOT NULL,"
            + Album.TITLE + " TEXT NOT NULL"
            + ")";
    private static final String CREATE_PHOTOS = ""
            + "CREATE TABLE " + Photo.TABLE + "("
            + Photo._ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Photo.ID + " INTEGER NOT NULL,"
            + Photo.ALBUM_ID + " INTEGER NOT NULL REFERENCES " + Album.TABLE + "(" + Album.ID + "),"
            + Photo.TITLE + " TEXT NOT NULL,"
            + Photo.URL + " TEXT NOT NULL,"
            + Photo.THUMBNAIL + " TEXT NOT NULL"
            + ")";
    private static final String CREATE_PHOTO_ALBUM_ID_INDEX =
            "CREATE INDEX photo_album_id ON " + Photo.TABLE +" (" + Photo.ALBUM_ID + ")";
    private static final String CREATE_PHOTO_HISTORY = ""
            + "CREATE TABLE " + Photo.HISTORY_TABLE + "("
            + Photo._ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Photo.ID + " INTEGER NOT NULL,"
            + Photo.ALBUM_ID + " INTEGER NOT NULL REFERENCES " + Album.TABLE + "(" + Album.ID + "),"
            + Photo.TITLE + " TEXT NOT NULL,"
            + Photo.URL + " TEXT NOT NULL,"
            + Photo.THUMBNAIL + " TEXT NOT NULL"
            + ")";

    public DbOpenHelper(Context context) {
        super(context, "test.db", null /* factory */, VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ALBUMS);
        db.execSQL(CREATE_PHOTOS);
        db.execSQL(CREATE_PHOTO_ALBUM_ID_INDEX);
        db.execSQL(CREATE_PHOTO_HISTORY);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}