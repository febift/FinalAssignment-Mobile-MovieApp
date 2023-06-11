package com.example.finalassignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalassignment.Model.MovieModel;
import com.example.finalassignment.Model.TvShowModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "favorites";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_SYNOPSIS = "synopsis";
    public static final String COLUMN_LANGUAGE = "language";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_RATING = "rating";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER , " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_YEAR + " TEXT, " +
                COLUMN_POSTER + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_BACKDROP + " TEXT, " +
                COLUMN_SYNOPSIS + " TEXT, " +
                COLUMN_LANGUAGE + " TEXT, " +
                COLUMN_POPULARITY + " REAL, " +
                COLUMN_RATING + " REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addMovieToFavorites(MovieModel movie, String type) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

            values.put(COLUMN_ID, movie.getId());
            values.put(COLUMN_TITLE, movie.getTitle());
            values.put(COLUMN_YEAR, movie.getRelease_date());
            values.put(COLUMN_POSTER, movie.getPoster_image());
            values.put(COLUMN_TYPE, type);
            values.put(COLUMN_BACKDROP, movie.getBackdrop_image());
            values.put(COLUMN_SYNOPSIS, movie.getSynopsis());
            values.put(COLUMN_LANGUAGE, movie.getLanguage());
            values.put(COLUMN_RATING, movie.getRating());

        database.insert(TABLE_NAME, null, values);

        database.close();
    } public void addTvShowToFavorites(TvShowModel tvShow, String type) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

            values.put(COLUMN_ID, tvShow.getId());
            values.put(COLUMN_TITLE, tvShow.getName());
            values.put(COLUMN_YEAR, tvShow.getFirst_air_date());
            values.put(COLUMN_POSTER, tvShow.getPoster_image());
            values.put(COLUMN_TYPE, type);
            values.put(COLUMN_BACKDROP, tvShow.getBackdrop_image());
            values.put(COLUMN_SYNOPSIS, tvShow.getSynopsis());
            values.put(COLUMN_LANGUAGE, tvShow.getLanguage());
            values.put(COLUMN_POPULARITY, tvShow.getPopularity().doubleValue());
            values.put(COLUMN_RATING, tvShow.getRating());

        database.insert(TABLE_NAME, null, values);

        database.close();
    }

    public List<Object> getFavorites(){
        List<Object> favoritesList = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            Object model = favoritesCursor(cursor);
            if (model != null){
                favoritesList.add(model);

            }
        }
        return  favoritesList;
    }

    private Object favoritesCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TYPE));
        String year = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_YEAR));
        String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_POSTER));
        String backdrop = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BACKDROP));
        String synopsis = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SYNOPSIS));
        String language = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LANGUAGE));
        Float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RATING));
        double popularity = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_POPULARITY));

        if (type.equals( "movie")){
            return new MovieModel(id, title, backdrop, poster, year, rating, synopsis,
            language,popularity);
        } else if (type.equals("tvshow")) {
            return new TvShowModel(id, title, backdrop, poster, year, rating, synopsis,
                    language,popularity);

        }
        return null;
    }

    public boolean isFavorite(int id_movie, int id_tvshow) {
        SQLiteDatabase database = this.getReadableDatabase();

        String selection = COLUMN_ID + " = ? OR " + COLUMN_ID+ " = ?";
        String[] selectionArgs = {String.valueOf(id_movie), String.valueOf(id_tvshow)};

        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isFavorite = cursor.getCount() > 0;

        cursor.close();
        database.close();

        return isFavorite;
    }

    public void removeFromFavorites(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        database.delete(TABLE_NAME, selection, selectionArgs);

        database.close();
    }


}

