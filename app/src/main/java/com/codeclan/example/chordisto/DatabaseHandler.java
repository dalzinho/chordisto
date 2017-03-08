package com.codeclan.example.chordisto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // this was coded by hand, but based on the tutorial I read here: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "songBook";
    private static final String TABLE_SONGS = "songs";

    private static final String KEY_ID = "id";
    private static final String KEY_SONG_TITLE = "songTitle";
    private static final String KEY_CHORDS = "chords";
    private static final String KEY_TEMPO = "tempo";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SONGS_TABLE = "CREATE TABLE " + TABLE_SONGS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SONG_TITLE + " TEXT, " + KEY_CHORDS + " TEXT, " + KEY_TEMPO + " INTEGER);";
        sqLiteDatabase.execSQL(CREATE_SONGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS + ";"); //i added the semi-colon string assuming a mistake. this may cause trouble
        onCreate(sqLiteDatabase);
    }

    public void addSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_SONG_TITLE, song.getSongTitle());
        contentValues.put(KEY_CHORDS, song.getChords());
        contentValues.put(KEY_TEMPO, song.getTempo());
        db.insert(TABLE_SONGS, null, contentValues);
        db.close();
    };

    public Song getSong(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] idArray = new String[] {String.valueOf(id)};
        Cursor cursor = db.rawQuery("SELECT * FROM SONGS WHERE " + KEY_ID + " =?;", idArray);


        if (cursor != null)
            cursor.moveToFirst();

        Song song = new Song
                (Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3)));
        return song;
    };

    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> songList = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Song song = new Song();
                song.setId(Integer.parseInt(cursor.getString(0)));
                song.setSongTitle(cursor.getString(1));
                song.setChords(cursor.getString(2));
                songList.add(song);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return songList;
    };

    public int updateSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SONG_TITLE, song.getSongTitle());
        contentValues.put(KEY_CHORDS, song.getChords());
        contentValues.put(KEY_TEMPO, song.getTempo());

        return db.update(TABLE_SONGS, contentValues, KEY_ID + "=?", new String[] {String.valueOf(song.getId())});
    }

    public void deleteSong(Song song){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SONGS, KEY_ID + "=?", new String[] {String.valueOf(song.getId())});
        db.close();
    };
}

