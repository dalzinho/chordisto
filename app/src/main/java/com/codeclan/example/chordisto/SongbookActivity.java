package com.codeclan.example.chordisto;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SongbookActivity extends AppCompatActivity {

    ListView listView;
    String[] songTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songbook);

        listView = (ListView) findViewById(R.id.songbook_list_view);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Song> songList = db.getAllSongs();
        ArrayList<String> songs= new ArrayList<String>();

        for (Song song : songList){
            songs.add(song.getSongTitle());
        }

        ArrayAdapter<String> titlesAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                songs);

    }
}
