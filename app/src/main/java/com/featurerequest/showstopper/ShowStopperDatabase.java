package com.featurerequest.showstopper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

/**
 * Created by kannon on 8/28/2015.
 */
public class ShowStopperDatabase extends SQLiteOpenHelper
{
    private static final String DB_NAME = "showstopper";
    private static final int DB_VERSION = 1;

    public static final String TABLE_SHOWS = "shows";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_MOVIE = "movie";
    public static final String COLUMN_BANNED = "banned";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_SYNOPSIS = "synopsis";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_AMAZON = "amazon";
    public static final String COLUMN_NETFLIX = "netflix";
    public static final String COLUMN_ITUNES = "itunes";
    public static final String COLUMN_GOOGLE = "google";
    public static final String COLUMN_IMDB_ID = "imdb";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_SHOWS + "("
            + COLUMN_ID + " integer primary key autoinrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_YEAR + " integer not null, "
            + COLUMN_GENRE + " text not null, "
            + COLUMN_MOVIE + " integer default 1, "
            + COLUMN_LENGTH + " integer not null, "
            + COLUMN_SYNOPSIS + " text not null, "
            + COLUMN_BANNED + " integer default 0,"
            + COLUMN_AMAZON + " integer default 0,"
            + COLUMN_NETFLIX + " integer default 0,"
            + COLUMN_ITUNES+ " integer default 0,"
            + COLUMN_GOOGLE + " integer default 0,"
            + COLUMN_IMDB_ID + " integer not null"
            + ");";

    public ShowStopperDatabase( Context context )
    {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( DATABASE_CREATE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        Log.w(  ShowStopperDatabase.class.getName(), "Upgrading database from " + oldVersion + " to " +
            newVersion + ", which will destroy all old data" );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOWS);
        onCreate(db);
    }
}
