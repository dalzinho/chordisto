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

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "songBook";
    private static final String TABLE_SONGS = "songs";

    private static final String KEY_ID = "id";
    private static final String KEY_SONG_TITLE = "songTitle";
    private static final String KEY_CHORDS = "chords";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SONGS_TABLE = "CREATE TABLE " + TABLE_SONGS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SONG_TITLE + " TEXT, " + TABLE_SONGS + " TEXT);";
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

        db.insert(TABLE_SONGS, null, contentValues);
        db.close();
    };

    public Song getSong(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SONGS, new String[] {KEY_ID, KEY_SONG_TITLE, KEY_CHORDS}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Song song = new Song(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return song;
    };

    public List<Song> getAllSongs(){
        List<Song> songList = new ArrayList<Song>();
        String selectQuery = "SELECT * FROM " + TABLE_SONGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Song song = new Song();
                song.setId(Integer.parseInt(cursor.getString(0)));
                song.setSongTitle(cursor.getString(1));
                song.setChords(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return songList;
    };

    public int updateSong(Song song){

    };

    public void deleteSong(Song song){

    };
}

