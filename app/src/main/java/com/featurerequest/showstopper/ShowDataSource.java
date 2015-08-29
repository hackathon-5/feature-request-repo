package com.featurerequest.showstopper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannon on 8/28/2015.
 */
public class ShowDataSource
{
    //Database fields
    private SQLiteDatabase db;
    private ShowStopperDatabase dbHelper;
    private String[] allColumns = {dbHelper.COLUMN_ID, dbHelper.COLUMN_TITLE, dbHelper.COLUMN_YEAR, dbHelper.COLUMN_GENRE, dbHelper.COLUMN_MOVIE,
            dbHelper.COLUMN_BANNED, dbHelper.COLUMN_RATING, dbHelper.COLUMN_SYNOPSIS, dbHelper.COLUMN_LENGTH, dbHelper.COLUMN_AMAZON,
            dbHelper.COLUMN_NETFLIX, dbHelper.COLUMN_ITUNES, dbHelper.COLUMN_GOOGLE, dbHelper.COLUMN_IMDB_ID };

    public ShowDataSource(Context context)
    {
        dbHelper = new ShowStopperDatabase(context);
    }

    public void open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Show createShow(String title, int year, String genre, boolean movie, boolean banned, int rating, String synopsis, int length, boolean amazon, boolean netflix, boolean itunes, boolean google, int imdb )
    {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_TITLE, title);
        values.put(dbHelper.COLUMN_YEAR, year);
        values.put(dbHelper.COLUMN_GENRE, genre);
        values.put(dbHelper.COLUMN_MOVIE, movie);
        values.put(dbHelper.COLUMN_BANNED, banned);
        values.put(dbHelper.COLUMN_RATING, rating);
        values.put(dbHelper.COLUMN_SYNOPSIS, synopsis);
        values.put(dbHelper.COLUMN_LENGTH, length);
        values.put(dbHelper.COLUMN_AMAZON, amazon);
        values.put(dbHelper.COLUMN_NETFLIX, netflix);
        values.put(dbHelper.COLUMN_ITUNES, itunes);
        values.put(dbHelper.COLUMN_GOOGLE, google);
        values.put(dbHelper.COLUMN_IMDB_ID, imdb );

        long insertId = db.insert(dbHelper.TABLE_SHOWS, null, values);

        Cursor cursor = db.query(dbHelper.TABLE_SHOWS,     //table
                allColumns,                                 //columns to return
                dbHelper.COLUMN_ID + " = " + insertId,      //where clause
                null,                                       //String[] selection args for ? in where clause
                null,                                       //String[] groupBy
                null,                                       //String[] having - filter for groups
                null);                                     //String[] orderBy

        cursor.moveToFirst();
        Show show = cursorToShow(cursor);
        cursor.close();
        return show;
    }

    public Show getShowByImdbId( int id )
    {
        Cursor cursor = db.query( dbHelper.TABLE_SHOWS,
                allColumns,
                dbHelper.COLUMN_IMDB_ID + " = " + id,
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        Show show = cursorToShow(cursor);
        cursor.close();
        return show;
    }

    public Show getShowById( long id )
    {
        Cursor cursor = db.query( dbHelper.TABLE_SHOWS,
                allColumns,
                dbHelper.COLUMN_ID + " = " + id,
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        Show show = cursorToShow(cursor);
        cursor.close();
        return show;
    }

    public Show getShowByTitleAndYear( String title, int year )
    {
        Cursor cursor = db.query( dbHelper.TABLE_SHOWS,
                allColumns,
                dbHelper.COLUMN_TITLE + " = " + title + " AND " + dbHelper.COLUMN_YEAR + " = " + year,
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        Show show = cursorToShow(cursor);
        cursor.close();
        return show;
    }

    public int banShowById( long id )
    {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_BANNED, 1);
        return db.update( dbHelper.TABLE_SHOWS,
                values,
                dbHelper.COLUMN_ID + " = " + id,
                null);
    }

    public int unbanShowById( long id )
    {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_BANNED, 0);
        return db.update( dbHelper.TABLE_SHOWS,
                values,
                dbHelper.COLUMN_ID + " = " + id,
                null);
    }

    public void deleteShow(Show show)
    {
        long id = show.getId();
        Log.w(ShowDataSource.class.getName(), "Show deleted with id: " + id);
        db.delete(dbHelper.TABLE_SHOWS, dbHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Show> getAllShows()
    {
        List<Show> allShows = new ArrayList<Show>();
        Cursor cursor = db.query( dbHelper.TABLE_SHOWS, allColumns, null, null, null, null, null );
        while( !cursor.isAfterLast() )
        {
            allShows.add( cursorToShow(cursor) );
            cursor.moveToNext();
        }

        return allShows;
    }

    private Show cursorToShow( Cursor cursor )
    {
        Show show = new Show();
        show.setId(cursor.getLong(0));
        show.setTitle(cursor.getString(1));
        show.setYear(cursor.getInt(2));
        show.setGenre(cursor.getString(3));
        show.setMovie(toBoolean(cursor.getInt(4)));
        show.setLength(cursor.getInt(5));
        show.setSynopsis(cursor.getString(6));
        show.setBanned(toBoolean(cursor.getInt(7)));
        show.setAmazon(toBoolean(cursor.getInt(8)));
        show.setNetflix(toBoolean(cursor.getInt(9)));
        show.setItunes(toBoolean(cursor.getInt(10)));
        show.setGoogle(toBoolean(cursor.getInt(11)));
        show.setImdb( cursor.getInt(12) );
        return show;
    }

    private boolean toBoolean( int i )
    {
        return i != 0?true:false;
    }
}
